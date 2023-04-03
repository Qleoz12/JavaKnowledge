package com.example.DFA;

import com.example.DFA.Utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class automateDrinksSellerMachine {

        public static void main(String[] args) {

            String json = "5";
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
            State zero = new RtState("Q0");
            State first = new RtState("Q1");
            State second = new RtState("Q2");
            State third = new RtState("Q3");
            State fourth = new RtState("Q4");
            State fifth = new RtState("Q5",true);

            State[] states = new State[]{zero,first, second, third, fourth, fifth};

            // Bucle que crea las transiciones entre los estados
            for (int i = 0; i < states.length; i++) {
                State state = states[i];
                if (i < states.length - 1) {
                    state = state.with(new RtTransition("1", states[i+1]));
                }
                if (i < states.length - 2) {
                    state = state.with(new RtTransition("2", states[i+2]));
                }
                if (i < states.length - 5) {
                    state = state.with(new RtTransition("5", fifth));
                }

            }

            FiniteStateMachineWithAditionalValidations machine= new RtFiniteStateMachineWithAditionalValidations(zero,new ArrayList<>());
            machine.AddiotonalValidations(s -> s.chars().map(Character::getNumericValue).sum() >= 5);
            return  machine;
        }

    }
