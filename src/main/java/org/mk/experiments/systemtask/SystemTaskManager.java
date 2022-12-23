package org.mk.experiments.systemtask;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class SystemTaskManager {


    /*
    public static void execute(Executable executable, String code, ExecutionProfile profile) {

        log.info("Start execution of: {}", code);
        executionReport(code, ExecutionStage.READY);

        if (!profile.getStartDelay().isZero()) {
            log.info("Delay execution of: {} for: {}", code, profile.getStartDelay());
            delayedStart(profile.getStartDelay());
            log.info("Delay execution of: {} completed", code);
        }

        boolean completedSuccessfully = false;
        Exception finalException = null;

        for (int i = 1; i <= profile.getRepetitionLimit(); ++i) {
            log.info("Start {} of {} execution for: {}", i, profile.getRepetitionLimit(), code);
            try {
                executable.execute();
                log.info("Execution nr {} for: {} completed successfully, no more execution required", i, code);
                completedSuccessfully = true;
                finalException = null;
                break;
            } catch (Exception ex) {
                log.info("Execution nr {} fro: {} error", i, code);
                log.error("", ex);
                finalException = ex;

                if (!profile.getRepetitionBreak().isZero() && i < profile.getRepetitionLimit()) {
                    log.info("Delay execution after error of: {} for: {}", code, profile.getStartDelay());
                    delayedStart(profile.getRepetitionBreak());
                    log.info("Delay execution after error of: {} completed", code);
                }
            }
        }

        if (completedSuccessfully) {
            log.info("Execution of: {} completed with success", code);
        } else {
            log.info("Execution of: {} completed with error: {}", code, finalException);
        }
    }

    private static void delayedStart(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executionReport(String code, ExecutionStage stage) {
        log.info("{}: Execution of {} stage set to: {}", LocalDateTime.now(), code, stage);
    }

     */
}
