package org.mk.experiments;

public class SplitExpExpenseCompleter {
    public SplitExpExpenseWhoBuilder next() {
        // TODO: add complete transaction to context list
        return new SplitExpExpenseWhoBuilder();
    }

    public SplitExpTotalBuilder showMe() {
        // TODO: finalize all calculations
        return new SplitExpTotalBuilder();
    }
}
