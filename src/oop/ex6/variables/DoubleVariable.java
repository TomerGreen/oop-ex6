package oop.ex6.variables;

import oop.ex6.variables.BooleanVariable;

/**
 * Represents a double variable.
 */
public class DoubleVariable extends BooleanVariable {

    private static final String typeName = "double";

    private static final String VALID_DOUBLE_VALUE = "-?\\d+(?:.\\d+)?";

    /**
     * Creates a double variable object.
     * @param name The variable name.
     * @param isFinal Whether it is defined as final.
     */
    public DoubleVariable(String name, boolean isFinal) {
        super(name, isFinal);
    }

    @Override
    public boolean isValidValue(String value) {
        return value.matches(VALID_DOUBLE_VALUE);
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}