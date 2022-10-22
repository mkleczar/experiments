package org.mk.experiments;

import org.mk.experiments.context.EventContext;

public class SplitExpBuilder {

    public static SplitExpWhoBuilder oneDay() {
        return new SplitExpWhoBuilder(new EventContext());
    }

}
