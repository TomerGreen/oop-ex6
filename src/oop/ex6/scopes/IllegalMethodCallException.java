package oop.ex6.scopes;

/**
 * Thrown when an illegal method call occurs.
 */
class IllegalMethodCallException extends Exception{

    private static final String DEFAULT_MSG = "Illegal method call";

    public IllegalMethodCallException() {
        super(DEFAULT_MSG);
    }

    public IllegalMethodCallException(String message) {
        super(message);
    }
}