package org.mk.experiments.systemtask;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SingleSystemTaskExecution {

    public static void execute(Executable executable, ExecutionProfile profile) {
        log.info("{} - Stage: {}", Thread.currentThread(), profile.getCurrentStage());

        if (profile.getCurrentStage() == ExecutionStage.NEW) {
            profile.setCurrentStage(ExecutionStage.READY);
            Executor executor = Executors.newSingleThreadExecutor();
            if (!profile.getStartDelay().isZero()) {
                //profile.setNextStart(LocalDateTime.now().plus(profile.getStartDelay()));
                // TODO: save profile to persistence unit;
                executor = CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS);
            }
            CompletableFuture.runAsync(
                    () -> execute(executable, profile), executor).t;
        } else if (profile.getCurrentStage() == ExecutionStage.READY || profile.getCurrentStage() == ExecutionStage.PARTIAL_FAILURE) {
            try {
                profile.setCurrentRepetition(profile.getCurrentRepetition() + 1);
                profile.setCurrentStage(ExecutionStage.RUNNING);
                log.info("Start execution no {}", profile.getCurrentRepetition());
                executable.execute(profile.getApplicationNumber());
                log.info("Complete execution no {}", profile.getCurrentRepetition());
                profile.setCurrentStage(ExecutionStage.SUCCESS);
            } catch (Exception ex) {
                Executor executor = Executors.newSingleThreadExecutor();
                if (profile.getCurrentRepetition() >= profile.getRepetitionLimit()) {
                    profile.setCurrentStage(ExecutionStage.FAILURE);
                } else {
                    profile.setCurrentStage(ExecutionStage.PARTIAL_FAILURE);
                    if (!profile.getRepetitionBreak().isZero()) {
                        //profile.setNextStart(LocalDateTime.now().plus(profile.getRepetitionBreak()));
                        executor = CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS);
                    }
                }
                CompletableFuture.runAsync(
                        () -> execute(executable, profile), executor);
            }
        } else if (profile.getCurrentStage() == ExecutionStage.FAILURE) {
        } else if (profile.getCurrentStage() == ExecutionStage.SUCCESS) {
        }
    }
}
