//package com.example.DFA;
//
//import com.example.DFA.Utils.FiniteStateMachine;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
//public class ClassIdentifierValidatorTest {
//
//    @Test
//    void acceptsCommon() {
//        String json = "ClassIdentifierValidatorTest";
//        FiniteStateMachine machine = ClassIdentifierValidator.buildJsonStateMachine();
//        for (int i = 0; i < json.length(); i++) {
//            machine = machine.switchState(String.valueOf(json.charAt(i)));
//        }
//        assertTrue(machine.canStop());
//    }
//
//    @Test
//    void acceptsDoblueUpercase() {
//        String json = "CClas";
//        FiniteStateMachine machine = ClassIdentifierValidator.buildJsonStateMachine();
//        for (int i = 0; i < json.length(); i++) {
//            machine = machine.switchState(String.valueOf(json.charAt(i)));
//        }
//        assertTrue(machine.canStop());
//    }
//
//    @Test
//    void acceptsDolaratstart() {
//        String json = "$CClas";
//        FiniteStateMachine machine = ClassIdentifierValidator.buildJsonStateMachine();
//        for (int i = 0; i < json.length(); i++) {
//            machine = machine.switchState(String.valueOf(json.charAt(i)));
//        }
//        assertTrue(machine.canStop());
//    }
//
//    @Test
//    void acceptsUnderScorestart() {
//        String json = "_CClas";
//        FiniteStateMachine machine = ClassIdentifierValidator.buildJsonStateMachine();
//        for (int i = 0; i < json.length(); i++) {
//            machine = machine.switchState(String.valueOf(json.charAt(i)));
//        }
//        assertTrue(machine.canStop());
//    }
//
//    @Test
//    void rejectslowerCaseAtStarts() {
//        String json = "cClas";
//        assertThrows(IllegalArgumentException.class, () -> {FiniteStateMachine machine = ClassIdentifierValidator.buildJsonStateMachine();
//        for (int i = 0; i < json.length(); i++) {
//            machine = machine.switchState(String.valueOf(json.charAt(i)));
//        }
//        });
//    }
//
//    @Test
//    void rejectsNumberAtStarts() {
//        String json = "1Clas";
//        assertThrows(IllegalArgumentException.class, () -> {FiniteStateMachine machine = ClassIdentifierValidator.buildJsonStateMachine();
//            for (int i = 0; i < json.length(); i++) {
//                machine = machine.switchState(String.valueOf(json.charAt(i)));
//            }
//        });
//    }
//
//    @Test
//    void rejectsWhiteSpacesAtMiddle() {
//        String json = "C Clas";
//        assertThrows(IllegalArgumentException.class, () -> {FiniteStateMachine machine = ClassIdentifierValidator.buildJsonStateMachine();
//            for (int i = 0; i < json.length(); i++) {
//                machine = machine.switchState(String.valueOf(json.charAt(i)));
//            }
//        });
//    }
//}
