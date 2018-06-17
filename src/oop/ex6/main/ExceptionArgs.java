package oop.ex6.main;

import java.io.IOException;

public class ExceptionArgs extends IOException{

    String msg;

    public ExceptionArgs(){}

    public ExceptionArgs(String message){ msg = message; }
}
