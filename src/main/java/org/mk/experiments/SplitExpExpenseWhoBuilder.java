package org.mk.experiments;

import lombok.AllArgsConstructor;
import org.mk.experiments.context.EventContext;
import org.mk.experiments.context.ExpenseContext;

@AllArgsConstructor
public class SplitExpExpenseWhoBuilder {

    private EventContext eventContext;

    public SplitExpExpenseHowMuchBuilder teamMember(String name) {
        ExpenseContext expenseContext = new ExpenseContext();
        // TODO: check if name is on the participants list
        expenseContext.setWho(name);
        return new SplitExpExpenseHowMuchBuilder(eventContext, expenseContext);
    }
}
