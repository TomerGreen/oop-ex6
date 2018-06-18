package oop.ex6.main;

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
     */
    public InvalidAssignmentException(String message, Exception cause) {
        super(message, cause);
    }
}
