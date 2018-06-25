package oop.ex6.variables;

import oop.ex6.main.LogicException;
import oop.ex6.variables.Variable;

/**
 * Thrown when an uninitialized variable is used.
 */
public class UninitializedVariableUsageException extends LogicException {

    /**
     * Creates a new exception.
     * @param var The uninitialized variable.
     */
    public UninitializedVariableUsageException(Variable var) {
        super("Cannot use uninitialized variable " + var.getName());
    }
}
