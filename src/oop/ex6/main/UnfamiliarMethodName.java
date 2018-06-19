package oop.ex6.main;


/**
 * Thrown when an undefined method name is referred.
 */
public class UnfamiliarMethodName extends LogicException{

    private static final String DEFAULT_MSG = "UnfamiliarMethodName";

    public UnfamiliarMethodName() {
        super(DEFAULT_MSG);
    }

    public UnfamiliarMethodName(String message, Exception cause) { super(message, cause); }

    public UnfamiliarMethodName(String message) {
        super(message);
    }
}
