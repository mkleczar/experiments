package org.mk.experiments.statemachine;

import lombok.extern.slf4j.Slf4j;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SystemTaskExecutor {

    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void async(Runnable task, Context context) {
        ExecutionStateMachine.<Context, String>builder()
                .getter(Context::getState)
                .setter(Context::setState)
                .repo(this::repo)
                .transition("NEW", ctx -> {
                    ctx.setState(ctx.getStartDelay().isZero() ? "EXEC" : "PENDING");
                    async(task, ctx);
                    return null;
                })
                .transition("PENDING", ctx -> {
                    CompletableFuture.runAsync(() -> { ctx.setState("EXEC"); async(task, ctx); },
                            CompletableFuture.delayedExecutor(ctx.getStartDelay().getSeconds(), TimeUnit.SECONDS));
                    return null;
                })
                .transition("ERROR", ctx -> {
                    CompletableFuture.runAsync(() -> { ctx.setState("EXEC"); async(task, ctx); },
                            CompletableFuture.delayedExecutor(ctx.getInBetweenDelay().getSeconds(), TimeUnit.SECONDS));
                    return null;
                })
                .transition("EXEC", ctx -> {
                    ctx.count++;
                    CompletableFuture.<Void>runAsync(task)
                            .thenRun(() -> {
                                ctx.state = "COMPLETED";
                                async(task, ctx);
                            })
                            .exceptionally(ex -> {
                                CompletableFuture.runAsync(() -> {
                                    ctx.setState(ctx.count < ctx.countLimit ? "ERROR" : "FAILURE");
                                    async(task, ctx); });
                                return null;
                            });
                    return null;
                })
                .transition("FAILURE", ctx -> {log.info("FAILURE"); return null;})
                .transition("COMPLETED", ctx -> {log.info("COMPLETED"); return null;})
                .step(context);
    }

    public void sync(Runnable task, Context context) {

        ExecutionStateMachine.<Context, String>builder()
                .getter(Context::getState)
                .setter(Context::setState)
                .repo(this::repo)
                .transition("NEW", ctxt -> ctxt.getStartDelay().isZero() ? "EXEC" : "PENDING")
                .transition("PENDING", ctxt -> {delay(ctxt.getStartDelay()); return "EXEC";})
                .transition("ERROR", ctxt -> {delay(ctxt.getInBetweenDelay()); return "EXEC";})
                .transition("EXEC", ctxt -> {
                    ctxt.count++;
                    try {
                        task.run();
                        return "COMPLETED";
                    } catch (Exception ex) {
                        if (ctxt.count < ctxt.countLimit) {
                            return "ERROR";
                        }
                    }
                    return "FAILURE";
                })
                .from(context);
    }


    private void delay(Duration duration) {
        // TODO: write delay procedure
        //log.info("Waiting for: {}", duration);
    }

    private void repo(Context context) {
        // TODO: write storing context to repository
        log.info("Store to database: {}", context);
    }
}
