package oop.ex6.main;

import java.util.HashMap;

public abstract class Scope {

    /** The root of the line tree that represents this scope. */
    protected ScopeNode root;

    /** The variables that were defined locally in the statement. */
    protected HashMap<String, Variable> variables;

    /** The statement whose scope encapsulates this statement. */
    protected Scope parent;

    /** The global statement this statement is a part of. */
//    GlobalStatement global;

    /**
     * Checks if a variable with that name already exists in the symbol table.
     * @param name The name to check.
     * @return Whether a variable with that name has been declared.
     */
    public boolean isDefined(String name) {
        return variables.containsKey(name);
    }

    private Variable parseVariableDec(String line) {
        return null;
    }

}
