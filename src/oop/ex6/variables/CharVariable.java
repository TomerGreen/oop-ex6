package oop.ex6.variables;

/**
 * Represents a char variable.
 */
public class CharVariable extends Variable {

    private static final String typeName = "char";

    private static final String VALID_CHAR_VALUE = "'.'";

    /**
     * Creates a char variable object.
     * @param name The variable name.
     * @param isFinal Whether it is defined as final.
     */
    CharVariable(String name, boolean isFinal) {
        super(name, isFinal);
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean isValidValue(String value) {
        return value.matches(VALID_CHAR_VALUE);
    }
}