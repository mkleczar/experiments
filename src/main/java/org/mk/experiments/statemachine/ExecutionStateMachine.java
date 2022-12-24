package org.mk.experiments.statemachine;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExecutionStateMachine<CTXT, STATE> {



    private Function<CTXT, STATE> getter;
    private BiConsumer<CTXT, STATE> setter;

    private Consumer<CTXT> repo;

    private final Map<STATE, Function<CTXT, STATE>> transitions = new HashMap<>();

    public static <E, S> ExecutionStateMachine<E, S> builder() {
        return new ExecutionStateMachine<>();
    }

    public ExecutionStateMachine<CTXT, STATE> getter(Function<CTXT, STATE> getter) {
        this.getter = getter;
        return this;
    }
    public ExecutionStateMachine<CTXT, STATE> setter(BiConsumer<CTXT, STATE> setter) {
        this.setter = setter;
        return this;
    }

    public ExecutionStateMachine<CTXT, STATE> repo(Consumer<CTXT> repo) {
        this.repo = repo;
        return this;
    }
    public ExecutionStateMachine<CTXT, STATE> transition(STATE startState, Function<CTXT, STATE> endStateSupplier) {
        transitions.put(startState, endStateSupplier);
        return this;
    }

    public CTXT from(CTXT startState) {
        return new StateMachineInstance().run(startState);
    }


    private class StateMachineInstance {
        public CTXT run(CTXT c) {
           STATE state = getter.apply(c);
           while (transitions.containsKey(state)) {
               state = transitions.get(state).apply(c);
               setter.accept(c, state);
               repo.accept(c);
           }
           return c;
        }
    }
}
