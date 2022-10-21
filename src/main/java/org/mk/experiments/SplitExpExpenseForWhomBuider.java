package org.mk.experiments;

public class SplitExpExpenseForWhomBuider {
    public SplitExpExpenseCompleter onBehalfOfAll() {
        // TODO: allocate expense for all
        return new SplitExpExpenseCompleter();
    }

    public SplitExpExpenseCompleter onBehalfOf(String ... whom) {
        // TODO: allocate expense for 'whom'
        return new SplitExpExpenseCompleter();

    }
}
