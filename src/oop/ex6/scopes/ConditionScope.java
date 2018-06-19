package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableParser;

public class ConditionScope extends Scope {

    private final static String AND_OR_REGEX = "(&&)|(\\|\\|)";

    public ConditionScope(LineNode root, Scope parent, String booleanCondition, GlobalScope globalScope) throws
            InvalidVariableDeclarationException, UnfamiliarMethodName, InvalidAssignmentException,
            IllegalMethodCallException, UninitializedVariableUsageException, UnfamiliarVariableTypeException {
        super(root, parent, globalScope);
        booleanConditionVerifier(booleanCondition);
        verifyScope();
    }

    private void booleanConditionVerifier(String conditionLine) throws UnfamiliarVariableTypeException,
            InvalidAssignmentException, UninitializedVariableUsageException {
        Variable dummy = VariableParser.createVariable("dummy", "boolean", false );
        String[] conditions = conditionLine.split(AND_OR_REGEX);
        for (String singleCondition : conditions) {
            verifyValueAssignment(dummy, singleCondition);
        }
    }
    
}
