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
    GlobalScope global;


    /**
     * A recursive method that looks for a variable with a given name and returns it.
     * Since it is recursive, it allows for a different implementation in ancestor scopes (for example,
     * searching several variable tables).
     * @param name The name of the variable to be searched.
     * @return The corresponding variable object.
     * @throws UnknownVariableException If the variable is not found in the local table, and the parent scope is null.
     */
    private Variable getDefinedVariable(String name) throws UnknownVariableException {
        if (this.variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parent == null) {
            throw new UnknownVariableException("Variable name \"" + name + "\" is undefined.");
        }
        return parent.getDefinedVariable(name);
    }

    /**
     * Checks if the assignment of the given value or variable to the given variable is legal.
     * @param var The variable object to which we want to assign.
     * @param value The value or name of the variable to be assigned.
     * @return Whether the assignment is valid.
     */
    private boolean isValidValueAssignment(Variable var, String value) throws InvalidAssignmentException,
            UnknownVariableException {
        if (var.isFinal() && var.isInitialized()) {
            throw new InvalidAssignmentException("Cannot assign value to final variable after declaration.");
        }
        // The assigned value is a variable.
        else if (VariableParser.isValidVarName(value)) {
            Variable assignee = getDefinedVariable(value);
        }
        else

    /**
     * @param line
     * @return
     */
    private Variable[] parseVariableDec(String line) {
        return null;
    }

}
