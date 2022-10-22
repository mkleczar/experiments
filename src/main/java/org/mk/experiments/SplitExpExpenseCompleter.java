package org.mk.experiments;

import lombok.AllArgsConstructor;
import org.mk.experiments.context.EventContext;
import org.mk.experiments.context.ExpenseContext;

@AllArgsConstructor
public class SplitExpExpenseCompleter {

    private EventContext eventContext;
    private ExpenseContext expenseContext;

    public SplitExpExpenseWhoBuilder next() {
        eventContext.getExpenses().add(expenseContext);
        return new SplitExpExpenseWhoBuilder(eventContext);
    }

    public SplitExpTotalBuilder showMe() {
        eventContext.getExpenses().add(expenseContext);

        // TODO: finalize all calculations

        return new SplitExpTotalBuilder(eventContext);
    }
}
