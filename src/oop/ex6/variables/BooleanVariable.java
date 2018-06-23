package oop.ex6.variables;

/**
 * Represents a boolean variable.
 */
public class BooleanVariable extends Variable {

    private static final String typeName = "boolean";

    private static final String VALID_BOOLEAN_VALUE = "true|false|-?\\d+(?:.\\d+)?";

    /**
     * Creates a boolean variable object.
     * @param name The variable name.
     * @param isFinal Whether it is defined as final.
     */
    BooleanVariable(String name, boolean isFinal) {
        super(name, isFinal);
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean isValidValue(String value) {
        return value.matches(VALID_BOOLEAN_VALUE);
    }
}