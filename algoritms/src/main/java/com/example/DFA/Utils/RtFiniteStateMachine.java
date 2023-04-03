package com.example.DFA.Utils;

/**
 * Default implementation of a finite state machine.
 * This class is immutable and thread-safe.
 */
public final class RtFiniteStateMachine implements FiniteStateMachine {

    /**
     * Current state.
     */
    private State current;

    /**
     * Ctor.
     * @param initial Initial state of this machine.
     */
    public RtFiniteStateMachine(final State initial) {
        this.current = initial;
    }

    public FiniteStateMachine switchState(final CharSequence c) {

        System.out.println("---"+c);
        FiniteStateMachine updated= new RtFiniteStateMachine(this.current.transit(c));
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
}
