package com.example.DFA.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Default implementation of a finite state machine.
 * This class is immutable and thread-safe.
 */
public final class RtFiniteStateMachineWithAditionalValidations implements FiniteStateMachineWithAditionalValidations {

    /**
     * Current state.
     */
    private State current;

    private List<Predicate<String>> validations=new ArrayList<>();

    /**
     * Ctor.
     * @param initial Initial state of this machine.
     */
    public RtFiniteStateMachineWithAditionalValidations(final State initial, List<Predicate<String>> validations ) {
        this.validations=validations;
        this.current = initial;
    }

    public RtFiniteStateMachineWithAditionalValidations switchState(final CharSequence c) {

        System.out.println("---"+c);
        RtFiniteStateMachineWithAditionalValidations updated= new RtFiniteStateMachineWithAditionalValidations(this.current.transit(c),validations);
        System.out.println(this.current.getName()+"---"+updated.getState().getName());


        return  updated;
    }

    public boolean canStop() {
        return this.current.isFinal();
    }

    @Override
    public State getState() {
        return this.current;
    }

    @Override
    public void AddiotonalValidations(Predicate<String> predicate) {
        validations.add(predicate);
    }

    @Override
    public boolean isValid(String input) {
        return validations.stream()
                .allMatch(x->x.test(input));

    }
}
