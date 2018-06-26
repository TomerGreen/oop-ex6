package oop.ex6.scopes;

import oop.ex6.main.LogicException;

/**
 * Thrown when method ends without return statement
 */
public class NoReturnException extends LogicException {

    public NoReturnException(String message, Exception cause) {
        super(message, cause);
    }

    public NoReturnException(String message) {
        super(message);
    }
}
