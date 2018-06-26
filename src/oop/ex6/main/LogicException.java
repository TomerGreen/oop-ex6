package oop.ex6.main;

import oop.ex6.variables.Variable;

/**
 * Thrown when there's a logical error in the program code.
 */
public abstract class LogicException extends Exception {

    /**
     * Creates a new exception object with the given message.
     * @param message The message.
     */
    protected LogicException(String message) {
        super(message);
    }

    /**
     * A constructor with a cause variable.
     * @param message The error message.
     * @param cause The cause exception.
     */
    protected LogicException(String message, Exception cause) {
        super(message, cause);
    }

}
