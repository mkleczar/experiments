package org.mk.experiments.financial;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Transfer {
    private String from;
    private String to;
    private String description;
    private BigDecimal amount;
}
