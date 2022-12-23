package org.mk.experiments.systemtask;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@Builder
public class ExecutionProfile {
    private Long id;
    private String code;
    private String applicationNumber;
    private int currentRepetition;
    private ExecutionStage currentStage;
    private Duration startDelay;
    private int repetitionLimit;
    private Duration repetitionBreak;
}
