package oop.ex6.main;

/**
 * Represents a String variable.
 */
public class StringVariable extends Variable {

    private static final String VALID_STRING_VALUE = "\".*\"";

    /**
     * Creates a boolean variable object.
     * @param name The variable name.
     * @param isFinal Whether it is defined as final.
     */
    public StringVariable(String name, boolean isFinal) {
        super(name, isFinal);
    }

    @Override
    public boolean isValidValue(String value) {
        return value.matches(VALID_STRING_VALUE);
    }
}