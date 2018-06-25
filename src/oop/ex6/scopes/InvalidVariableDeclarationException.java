package oop.ex6.scopes;

import oop.ex6.main.LogicException;

/**
 * Thrown when an invalid declaration is encountered.
 */
public class InvalidVariableDeclarationException extends LogicException {

    /**
     * Creates a new exception.
     * @param message The exception message.
     */
    public InvalidVariableDeclarationException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * A constructor with no cause.
     * @param message The exception message.
     */
    public InvalidVariableDeclarationException(String message) {
        super(message);
    }
}
