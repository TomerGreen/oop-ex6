public abstract class Variable {

    private String name;

    private String type;

    private Boolean isFinal;


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

}
