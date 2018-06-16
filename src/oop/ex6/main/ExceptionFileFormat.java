package oop.ex6.main;

public class ExceptionFileFormat extends Throwable {

    String msg;

    public ExceptionFileFormat(){}

    public ExceptionFileFormat(String message){ msg = message;}
}
