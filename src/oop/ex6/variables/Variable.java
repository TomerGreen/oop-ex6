package oop.ex6.variables;

public abstract class Variable {

    /* The varname */
    private String name;

    /* Whether the variable is defined as final. */
    private boolean isFinal;

    /* Whether the variable was set an initial value. */
    private boolean isInitialized;


    public Variable(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
        isInitialized = false;
    }

    /**
     * @return The variable's name.
     */
    public String getName(){
        return name;
    }

    /**
     * @return The name of the variable type.
     */
    public abstract String getTypeName();

    /**
     * @return Whether the variable was declared as final.
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * @return Whether the variable was assigned an initial value.
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Makes the variable initialized
     */
    public void initialize(){
        isInitialized = true;
    }

    /**
     * Checks if assigned value is formatted properly for that variable type.
     * @param value the checked value
     * @return if valid or not
     */
    public abstract boolean isValidValue(String value);

}
