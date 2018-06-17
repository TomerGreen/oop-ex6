package oop.ex6.main;

/**
 * Thrown when trying to declare a variable using an existing unoverridable variable name
 */
public class VariableDeclarationException extends LogicException {

    /**
     * Creates a new exception.
     * @param varName The undefinable variable name.
     */
    public VariableDeclarationException(String varName) {
        super("Cannot override existing variable '" + varName + "'.");
    }
}
