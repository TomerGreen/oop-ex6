package oop.ex6.scopes;

/**
 * Thrown when an undefined variable name is referred.
 */
public class UnknownVariableException extends Exception {

    public UnknownVariableException(String message) {
        super(message);
    }
}
