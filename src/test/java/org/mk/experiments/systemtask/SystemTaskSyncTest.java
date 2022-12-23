package org.mk.experiments.systemtask;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@Slf4j
public class SystemTaskSyncTest {

    private static ExecutionProfile profile = ExecutionProfile.builder()
            .id(0L)
            .code("SIMPLE_TASK")
            .applicationNumber("12345678")
            .currentStage(ExecutionStage.NEW)
            .currentRepetition(0)
            .startDelay(Duration.parse("PT1S"))
            .repetitionLimit(3)
            .repetitionBreak(Duration.parse("PT1S"))
            .build();

    @Test
    public void simpleTaskSuccessTest() throws Exception {
        Executable executable = applicationNumber -> log.info("execute for: {}", applicationNumber);
        SingleSystemTaskExecution.execute(executable, profile);
        Thread.sleep(2000);
    }

    @Test
    public void simpleTaskErrorTest() throws Exception {
        Executable executable = applicationNumber -> {throw new RuntimeException("internal error for: " + applicationNumber);};
        SingleSystemTaskExecution.execute(executable, profile);
        log.info("{} - Execution called", Thread.currentThread());
        Thread.sleep(8000);
    }
}
