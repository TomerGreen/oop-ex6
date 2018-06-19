package oop.ex6.main;

public class UnrecognizedVariableTypeException extends LogicException {

    public UnrecognizedVariableTypeException(String type) {
        super("Unfamiliar variable type '" + type + "'.");
    }
}
