package oop.ex6.main;

public class ExceptionFileFormat extends SyntaxException {

    private final static String DEFAULT_MESSAGE = "file format is illegal";

    public ExceptionFileFormat(){
        super(DEFAULT_MESSAGE);
    }

    public ExceptionFileFormat(String message){
        super(message);
    }
}
