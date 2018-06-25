package oop.ex6.scopes;

/**
 * Thrown when the parameter list is invalid.
 */
public class InvalidParameterListException extends InvalidVariableDeclarationException {

    /**
     * Constructor with cause.
     */
    public InvalidParameterListException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * A rebel without a cause.
     */
    public InvalidParameterListException(String message) {
        super(message);
    }
}
