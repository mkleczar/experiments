package org.mk.experiments;

import lombok.AllArgsConstructor;
import org.mk.experiments.context.EventContext;

@AllArgsConstructor
public class SplitExpEventBuilder {

    private EventContext context;

    public SplitExpExpenseWhoBuilder decidedToOrganizeEvent(String name) {
        context.setName(name);
        return new SplitExpExpenseWhoBuilder(context);
    }
}
