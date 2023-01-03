package org.mk.experiments.statemachine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Slf4j
public class ExecutionStateMachineTest {

    @Test
    public void eventStateMachineTest() {

        Context state = Context.builder()
                .state("NEW")
                .count(0)
                .countLimit(3)
                .startDelay(Duration.parse("PT1S"))
                .inBetweenDelay(Duration.parse("PT3S"))
                .build();
        new SystemTaskExecutor().sync(this::task, state);
        log.info("-----");
        Context stateA = Context.builder()
                .state("NEW")
                .count(0)
                .countLimit(3)
                .startDelay(Duration.parse("PT1S"))
                .inBetweenDelay(Duration.parse("PT3S"))
                .build();
        new SystemTaskExecutor().sync(this::failureTask, stateA);
        log.info("-----");
        Context state2 = Context.builder()
                .state("ERROR")
                .count(2)
                .countLimit(3)
                .startDelay(Duration.parse("PT1S"))
                .inBetweenDelay(Duration.parse("PT3S"))
                .build();
        new SystemTaskExecutor().sync(this::task, state2);
    }

    @Test
    public void asyncExecutorTest() throws InterruptedException {

        Context state = Context.builder()
                .state("NEW")
                .count(0)
                .countLimit(3)
                .startDelay(Duration.parse("PT1S"))
                .inBetweenDelay(Duration.parse("PT3S"))
                .build();
        //new SystemTaskExecutor().async(this::task, state);
        new SystemTaskExecutor().async(this::failureTask, state);

        log.info("Async call executed");
        TimeUnit.SECONDS.sleep(10);
    }

    private void task() {
        log.info("Execute task");
    }
    private void failureTask() {
        log.info("Task failure");
        throw new RuntimeException("failure");
    }


}
