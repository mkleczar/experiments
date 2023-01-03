package org.mk.experiments.statemachine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;

@Getter
@Setter
@Builder
@ToString
public class Context {
    String taskName;
    String applicationNumber;
    String state;
    int count;
    int countLimit;
    Duration startDelay;
    Duration inBetweenDelay;
}
