package oop.ex6.variables;

/**
 * Represents the content of a single variable assignment line.
 */
public class VariableAssignment {

    /** The target variable name. */
    private String target;

    /** The assigned primitive value or variable name. */
    private String value;

    /**
     * Creates an assignment object.
     * @param target The target variable name.
     * @param value The value.
     */
    public VariableAssignment(String target, String value) {
        this.target = target;
        this.value = value;
    }

    /**
     * @return The target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @return The value.
     */
    public String getValue() {
        return value;
    }
}
