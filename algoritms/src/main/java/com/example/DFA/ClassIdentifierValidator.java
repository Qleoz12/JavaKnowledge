package com.example.DFA;


import com.example.DFA.Utils.*;

/**
 * https://www.w3schools.com/java/java_variables_identifiers.asp
 * https://comp.anu.edu.au/courses/comp1600/assets/lectures/07-dfa.pdf
 * digraph identifier {
 *     rankdir=LR;
 *     node [shape = circle];
 *     start [shape = point];
 *     start -> q0;
 *     q0 [label="q0",];
 *     q1 [label="q1"];
 *     q2 [label="q2", shape=doublecircle];
 *     q0 -> q1 [label="[A-Z_]"];
 *     q1 -> q1 [label="[a-zA-Z0-9_]"];
 *     q1 -> q2 [label=" "];
 * }

 */
public class ClassIdentifierValidator {
    public static void main(String[] args) {

        String json = "PPpe";
        FiniteStateMachine machine = buildJsonStateMachine();
        for (int i = 0; i < json.length(); i++) {
            machine = machine.switchState(String.valueOf(json.charAt(i)));
        }
        System.out.println(machine.getState().getName());
        System.out.println(machine.canStop());
    }

    /**
     * Builds a finite state machine to validate a simple
     * Json object.
     * @return
     */
    public static FiniteStateMachine buildJsonStateMachine() {
        State first = new RtState("Q1");
        State second = new RtState("Q2",true);
        State third = new RtState("Q3",true);

        //Add transitions with chars a-z,A-z,_
        for (int i = 0; i < 26; i++) {
            if (i < 10) {
                second = second.with(new RtTransition(String.valueOf(i), second));
            }
            first = first.with(new RtTransition(String.valueOf((char) ('a' + i)).toUpperCase(), second));

            second = second.with(new RtTransition(String.valueOf((char) ('a' + i)), second));
            second = second.with(new RtTransition(String.valueOf((char) ('a' + i)).toUpperCase(), second));
        }

        first = first.with(new RtTransition("$", second));
        first = first.with(new RtTransition("_", second));



        return new RtFiniteStateMachine(first);
    }




}
