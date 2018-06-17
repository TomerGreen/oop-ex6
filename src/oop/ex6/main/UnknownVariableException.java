package oop.ex6.main;

/**
 * Thrown when an undefined variable name is referred.
 */
public class UnknownVariableException extends Exception {

    public UnknownVariableException(String message) {
        super(message);
    }
}
