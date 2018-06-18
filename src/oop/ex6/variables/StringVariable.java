package oop.ex6.variables;

import oop.ex6.variables.Variable;

/**
 * Represents a String variable.
 */
public class StringVariable extends Variable {

    private static final String typeName = "String";

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
    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean isValidValue(String value) {
        return value.matches(VALID_STRING_VALUE);
    }
}