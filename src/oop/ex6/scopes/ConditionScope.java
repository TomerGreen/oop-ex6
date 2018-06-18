package oop.ex6.scopes;

import oop.ex6.main.LineNode;

public class ConditionScope extends Scope {

    public ConditionScope(LineNode root, Scope parent){
        super(root, parent);
        DecVerifier();
        verifyScope();
    }

    private void DecVerifier(){
        String line = root.getData();
    }
    
}
