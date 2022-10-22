package org.mk.experiments;

import lombok.AllArgsConstructor;
import org.mk.experiments.context.EventContext;
import org.mk.experiments.context.ExpenseContext;

import java.math.BigDecimal;

@AllArgsConstructor
public class SplitExpExpenseHowMuchBuilder {

    private EventContext eventContext;
    private ExpenseContext expenseContext;

    public SplitExpExpenseOnBuilder spent(Currency howMuch) {
        expenseContext.setAmount(howMuch);
        return new SplitExpExpenseOnBuilder(eventContext, expenseContext);
    }
}
