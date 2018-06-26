package oop.ex6.scopes;

import oop.ex6.main.LogicException;

/**
 * Thrown when something is wrong with a variable assignment.
 */
public class InvalidAssignmentException extends LogicException {

    /**
     * Creates a new exception object with the given message.
     *
     * @param message The message.
     */
    public InvalidAssignmentException(String message) {
        super(message);
    }

    /**
     * A constructor with cause.
     * @param message The message.
     * @param cause the cause for the exception
     */
    public InvalidAssignmentException(String message, Exception cause) {
        super(message, cause);
    }
}
