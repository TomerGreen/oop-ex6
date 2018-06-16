package oop.ex6.main;

import java.util.HashMap;

public class GlobalScope extends Scope {

    private HashMap<String, Method> methods;

    public GlobalScope(LineTree tree) {
        this.root = tree.root;
    }

}
