package oop.ex6.main;

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
}
