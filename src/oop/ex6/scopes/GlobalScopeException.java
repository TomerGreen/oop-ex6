package oop.ex6.scopes;

import oop.ex6.main.LogicException;

/**
 * Thrown when there's problem with the GlobalScope validation
 */
public class GlobalScopeException extends LogicException {

    GlobalScopeException(String message, Exception cause) {
        super(message, cause);
    }
}
