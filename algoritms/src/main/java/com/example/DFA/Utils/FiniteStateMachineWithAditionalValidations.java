package com.example.DFA.Utils;

import java.util.function.Predicate;

public interface FiniteStateMachineWithAditionalValidations extends FiniteStateMachine{

    void AddiotonalValidations(Predicate<String> predicate);

    boolean isValid(String input);
}
