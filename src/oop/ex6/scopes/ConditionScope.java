package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableParser;

public class ConditionScope extends Scope {

    private final static String AND_OR_REGEX = "(&&)|(\\|\\|)";

    ConditionScope(LineNode root, Scope parent, String booleanCondition, GlobalScope globalScope) throws LogicException{
        super(root, parent, globalScope);
        booleanConditionVerifier(booleanCondition);
        verifyScope();
    }

    private void booleanConditionVerifier(String conditionLine) throws LogicException{
        Variable dummy = VariableParser.createVariable("dummy", "boolean", false );
        String[] conditions = conditionLine.split(AND_OR_REGEX);
        for (String singleCondition : conditions) {
            verifyValueAssignment(dummy, singleCondition.trim());
        }
    }

}
