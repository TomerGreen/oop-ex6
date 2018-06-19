package oop.ex6.main;


/**
 * Thrown when an undefined method name is referred.
 */
public class UnfamiliarMethodName extends Exception{

    private static final String DEFAULT_MSG = "UnfamiliarMethodName";

    public UnfamiliarMethodName() {
        super(DEFAULT_MSG);
    }

    public UnfamiliarMethodName(String message) {
        super(message);
    }
}
