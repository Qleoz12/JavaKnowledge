package com.example.DFA.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * State in a finite state machine.
 */
public final class RtState implements State {

    private List<Transition> transitions;
    private boolean isFinal;
    private String name;

    
    public RtState(String name,final boolean isFinal) {
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
        this.name=name;
    }

    public RtState(String name) {
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
        this.name=name;
    }

    public State transit(final CharSequence c) {
        State pass= transitions
          .stream()
          .filter(t -> t.isPossible(c))
          .map(Transition::state)
          .findAny()
          .orElseThrow(() -> new IllegalArgumentException("Input not accepted: " + c));
        System.out.println("passing "+pass.getName());

        return pass;
    }

    public boolean isFinal() {
        return this.isFinal;
    }


    @Override
    public State with(Transition tr) {
        this.transitions.add(tr);
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
