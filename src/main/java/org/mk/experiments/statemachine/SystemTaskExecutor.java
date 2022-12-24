package org.mk.experiments.statemachine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
       if (context.getState().equals("NEW")) {
           log.info("NEW");
           context.setState(context.getStartDelay().isZero() ? "EXEC" : "PENDING");
           async(task, context);
       } else if (context.getState().equals("PENDING")) {
           log.info("PENDING");
           CompletableFuture.runAsync(() -> { context.setState("EXEC"); async(task, context); },
                   CompletableFuture.delayedExecutor(context.getStartDelay().getSeconds(), TimeUnit.SECONDS));
       } else if (context.getState().equals("ERROR")) {
           log.info("ERROR");
           CompletableFuture.runAsync(() -> { context.setState("EXEC"); async(task, context); },
                   CompletableFuture.delayedExecutor(context.getInBetweenDelay().getSeconds(), TimeUnit.SECONDS));
       } else if (context.getState().equals("EXEC")) {
           log.info("EXEC");
           context.count++;
           CompletableFuture.<Void>runAsync(task)
                   .thenRun(() -> {
                       context.state = "COMPLETED";
                       async(task, context);
                   })
                   .exceptionally(ex -> {
                       CompletableFuture.runAsync(() -> {
                           context.setState(context.count < context.countLimit ? "PENDING" : "FAILURE");
                           async(task, context); });
                       return null;
                   });
       } else if (context.getState().equals("COMPLETED")) {
           log.info("COMPLETED");
       } else if (context.getState().equals("FAILURE")) {
           log.info("FAILURE");
       }
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

    @Getter
    @Setter
    @Builder
    @ToString
    public static class Context {
        String state;
        int count;
        int countLimit;
        Duration startDelay;
        Duration inBetweenDelay;
    }

    private void delay(Duration duration) {
        // TODO: write delay procedure
        log.info("Waiting for: {}", duration);
    }

    private void repo(Context context) {
        // TODO: write storing context to repository
        log.info("Store to database: {}", context);
    }
}
