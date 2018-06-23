package oop.ex6.scopes;

import oop.ex6.main.LogicException;

public class ScopeException extends LogicException {

    ScopeException(String message, Exception cause) {
        super(message, cause);
    }
}
