package org.mk.experiments.context;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EventContext {
    private String name;
    private List<String> participants;
    private List<ExpenseContext> expenses = new ArrayList<>();
}
