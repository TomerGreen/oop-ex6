package oop.ex6.scopes;

import oop.ex6.main.InvalidVariableDeclarationException;
import oop.ex6.main.LineNode;
import oop.ex6.main.UnfamiliarMethodName;

public class ConditionScope extends Scope {

    public ConditionScope(LineNode root, Scope parent, String booleanCondition, GlobalScope globalScope) throws
            InvalidVariableDeclarationException, UnfamiliarMethodName {
        super(root, parent, globalScope);
        booleanConditionVerifier();
        verifyScope();
    }

    private void booleanConditionVerifier()
    {
        //TODO check boolean condition
    }

}
