package org.mk.experiments;

import lombok.AllArgsConstructor;
import org.mk.experiments.context.EventContext;
import org.mk.experiments.context.ExpenseContext;

import java.util.List;

@AllArgsConstructor
public class SplitExpExpenseForWhomBuider {

    private EventContext eventContext;
    private ExpenseContext expenseContext;

    public SplitExpExpenseCompleter onBehalfOfAll() {
        expenseContext.setForWhom(eventContext.getParticipants());
        return new SplitExpExpenseCompleter(eventContext, expenseContext);
    }

    public SplitExpExpenseCompleter onBehalfOf(String ... whom) {
        // TODO: check if person on the list are also on participants list in eventContext
        expenseContext.setForWhom(List.of(whom));
        return new SplitExpExpenseCompleter(eventContext, expenseContext);

    }
}
