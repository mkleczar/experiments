package org.mk.experiments.context;

import lombok.Data;
import org.mk.experiments.Currency;

import java.util.List;

@Data
public class ExpenseContext {
    private String who;
    private Currency amount;
    private String forWhat;
    private List<String> forWhom;

}
