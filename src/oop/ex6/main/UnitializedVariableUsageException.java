package oop.ex6.main;

/**
 * Thrown when an uninitialized variable is used.
 */
public class UnitializedVariableUsageException extends LogicException {

    /**
     * Creates a new exception.
     * @param var The uninitialized variable.
     */
    public UnitializedVariableUsageException(Variable var) {
        super("Cannot use uninitialized variable " + var.getName());
    }
}
