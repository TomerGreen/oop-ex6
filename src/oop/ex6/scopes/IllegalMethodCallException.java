package oop.ex6.scopes;

import oop.ex6.main.LogicException;

/**
 * Thrown when an illegal method call occurs.
 */
class IllegalMethodCallException extends LogicException{

    private static final String DEFAULT_MSG = "Illegal method call";

    public IllegalMethodCallException() {
        super(DEFAULT_MSG);
    }

    public IllegalMethodCallException(String message) {
        super(message);
    }
}