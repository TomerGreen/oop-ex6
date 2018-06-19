package oop.ex6.main;

public class NoReturnException extends LogicException{


    public NoReturnException(String message, Exception cause) {
        super(message, cause);
    }

    public NoReturnException(String message) {
        super(message);
    }
}
