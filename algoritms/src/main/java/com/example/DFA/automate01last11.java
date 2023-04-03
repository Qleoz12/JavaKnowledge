package com.example.DFA;

import com.example.DFA.Utils.*;

import java.util.ArrayList;

/**
 *digraph DFA {
 *     rankdir=LR;
 *     node [shape=circle];
 *     start [shape=point];
 *     start -> q0;
 *     q0 [style=""];
 *     q1 [shape=doublecircle];
 *     q0 -> q0 [label="0"];
 *     q0 -> q1 [label="1"];
 *     q1 -> q1 [label="0,1"];
 * }
 * */
public class automate01last11 {

        public static void main(String[] args) {

            String json = "111111101";
            FiniteStateMachineWithAditionalValidations machine = buildJsonStateMachine();
            for (int i = 0; i < json.length(); i++) {
                machine = (FiniteStateMachineWithAditionalValidations) machine.switchState(String.valueOf(json.charAt(i)));
            }
            System.out.println(machine.getState().getName());
            System.out.println(machine.canStop());
            System.out.println(machine.isValid(json));
        }

        /**
         * Builds a finite state machine to validate a simple
         * Json object.
         * @return
         */
        public static FiniteStateMachineWithAditionalValidations buildJsonStateMachine() {
            State first = new RtState("Q1");
            State second = new RtState("Q2",true);


            first = first.with(new RtTransition("0", second));
            first = first.with(new RtTransition("1", second));

            second = second.with(new RtTransition("0", second));
            second = second.with(new RtTransition("1", second));



            FiniteStateMachineWithAditionalValidations machine= new RtFiniteStateMachineWithAditionalValidations(first,new ArrayList<>());
            machine.AddiotonalValidations(s -> s.endsWith("11"));
            return  machine;
        }

    }
