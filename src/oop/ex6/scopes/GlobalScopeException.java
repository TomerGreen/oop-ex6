package oop.ex6.scopes;

import oop.ex6.main.LogicException;

public class GlobalScopeException extends LogicException {

    GlobalScopeException(String message, Exception cause) {
        super(message, cause);
    }
}
