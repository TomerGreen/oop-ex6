package oop.ex6.scopes;

import oop.ex6.main.LogicException;

/**
 * Thrown when Scope validation fail
 */
public class ScopeException extends LogicException {

    ScopeException(String message, Exception cause) {
        super(message, cause);
    }
}
