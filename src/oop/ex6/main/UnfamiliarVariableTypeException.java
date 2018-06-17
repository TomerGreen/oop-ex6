package oop.ex6.main;

public class UnfamiliarVariableTypeException extends LogicException {

    public UnfamiliarVariableTypeException(String type) {
        super("Unfamiliar variable type '" + type + "'.");
    }
}
