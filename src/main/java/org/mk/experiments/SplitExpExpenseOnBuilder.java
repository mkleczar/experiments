package org.mk.experiments;

import lombok.AllArgsConstructor;
import org.mk.experiments.context.EventContext;
import org.mk.experiments.context.ExpenseContext;

@AllArgsConstructor
public class SplitExpExpenseOnBuilder {

    private EventContext eventContext;
    private ExpenseContext expenseContext;

    public SplitExpExpenseForWhomBuider on(String somethingToBuy) {
        expenseContext.setForWhat(somethingToBuy);
        return new SplitExpExpenseForWhomBuider(eventContext, expenseContext);
    }
}
