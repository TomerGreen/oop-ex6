package oop.ex6.scopes;

/**
 * Thrown when the parameter list is invalid.
 */
public class InvalidParameterListException extends InvalidVariableDeclarationException {

    /**
     * Constructor with cause.
     * @param message the printed message
     * @param cause the cause of the specific exception
     */
    public InvalidParameterListException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * A rebel without a cause.
     * @param message the printed message
     */
    public InvalidParameterListException(String message) {
        super(message);
    }
}
