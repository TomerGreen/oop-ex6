package oop.ex6.variables;

import oop.ex6.variables.DoubleVariable;

/**
 * Represents an integer variable.
 */
public class IntVariable extends DoubleVariable {

    private static final String typeName = "int";

    private static final String VALID_INT_VALUE = "-?\\d+";

    /**
     * Creates an int variable object.
     * @param name The var name.
     * @param isFinal Whether it's final.
     */
    public IntVariable(String name, boolean isFinal) {
        super(name, isFinal);
    }

    @Override
    public boolean isValidValue(String value) {
        return value.matches(VALID_INT_VALUE);
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
