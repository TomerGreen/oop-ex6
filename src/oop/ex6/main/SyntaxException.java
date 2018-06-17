package oop.ex6.main;

/**
 * Thrown when there's a syntax error in the code.
 */
public class SyntaxException extends Exception {

    /**
     * Creates a new syntax exception object.
     * @param message The message to display.
     */
    public SyntaxException(String message) {
        super(message);
    }
}
