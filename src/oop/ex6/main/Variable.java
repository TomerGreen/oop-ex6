package oop.ex6.main;

public abstract class Variable {

    private String name;

    private String type;

    private boolean isFinal;

    private boolean isInitialized;


    public Variable(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    /**
     * Checks if assigned value is formatted properly for that variable type.
     * @param value
     * @return
     */
    public abstract boolean isValidValue(String value);

}
