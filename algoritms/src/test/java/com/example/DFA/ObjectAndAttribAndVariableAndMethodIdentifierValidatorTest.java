//package com.example.DFA;
//
//
//import com.example.DFA.Utils.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
///**
// *
// * https://comp.anu.edu.au/courses/comp1600/assets/lectures/07-dfa.pdf
// * digraph identifier {
// *     rankdir=LR;
// *     node [shape = circle];
// *     start [shape = point];
// *     start -> q0;
// *     q0 [label="q0",];
// *     q1 [label="q1"];
// *     q2 [label="q2", shape=doublecircle];
// *     q0 -> q1 [label="[a-z_]"];
// *     q1 -> q1 [label="[a-zA-Z0-9_]"];
// *     q1 -> q2 [label=" "];
// * }
//
// */
//class ObjectAndAttribAndVariableAndMethodIdentifierValidatorTest {
//    @Test
//    void rejectsCommon() {
//        String json = "ClassIdentifierValidatorTest";
//        assertThrows(IllegalArgumentException.class, () -> validate(json));
//    }
//
//    private void validate(String value) {
//        FiniteStateMachine machine = ObjectAndAttribAndVariableAndMethodIdentifierValidator.buildJsonStateMachine();
//        for (int i = 0; i < value.length(); i++) {
//            machine = machine.switchState(String.valueOf(value.charAt(i)));
//        }
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"$CClas", "cClas", "_CClas"})
////    @Test
//    void acceptsDolaratstart(String json) {
//        FiniteStateMachine machine = ObjectAndAttribAndVariableAndMethodIdentifierValidator.buildJsonStateMachine();
//        for (int i = 0; i < json.length(); i++) {
//            machine = machine.switchState(String.valueOf(json.charAt(i)));
//        }
//        assertTrue(machine.canStop());
//    }
//
//
//    @Test
//    void rejectsNumberAtStarts() {
//        String json = "1Clas";
//        assertThrows(IllegalArgumentException.class, () -> validate(json));
//    }
//
//    @Test
//    void rejectsWhiteSpacesAtMiddle() {
//        String json = "C Clas";
//        assertThrows(IllegalArgumentException.class, () -> validate(json));
//    }
//
//
//    @ParameterizedTest
//    @ValueSource(strings = {"ab11c2x", "valor12_minimo2"})
////    @Test
//    void testProposed(String json) {
//        FiniteStateMachine machine = ObjectAndAttribAndVariableAndMethodIdentifierValidator.buildJsonStateMachine();
//        for (int i = 0; i < json.length(); i++) {
//            machine = machine.switchState(String.valueOf(json.charAt(i)));
//        }
//        assertTrue(machine.canStop());
//    }
//
//
//}
