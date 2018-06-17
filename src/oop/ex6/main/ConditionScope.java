package oop.ex6.main;

public class ConditionScope extends Scope {

    ConditionScope(LineNode root, Scope parent){
        super(root, parent);
        DecVerifier();
        verifyScope();
    }

    private void DecVerifier(){
        String line = root.data;

    }


}
