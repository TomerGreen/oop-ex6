package oop.ex6.main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodScope extends Scope{


    private static final String METHODS_FIRST_PART_REGEX = "(void) ([a-zA-z]+\\w*) ?\\( ?";
    private static final String METHODS_SECOND_PART_REGEX = "\\) ?\\{";
    private static final String ILLEGAL_METHOD_DECELERATION = "illegal method deceleration";
    private static final int NAME_PLC = 3;

    String name;

    ArrayList<Variable> argList;

    public MethodScope(LineNode root, Scope parent, String name){
        super(root, parent);
        this.name = name;
        argList = new ArrayList<>();
    }

    private void methodDecAnalyzer(String deceleration) throws ExceptionFileFormat {
        String methodName;
        MethodScope currMethod;
        ArrayList<Variable> argList;
        Pattern begDeclarePattern = Pattern.compile(METHODS_FIRST_PART_REGEX);
        Pattern endDeclarePattern = Pattern.compile(METHODS_SECOND_PART_REGEX);
        Matcher begDeclareMatcher = begDeclarePattern.matcher(deceleration);
        if(!begDeclareMatcher.find())
            throw new ExceptionFileFormat( ILLEGAL_METHOD_DECELERATION);
        methodName = begDeclareMatcher.group(NAME_PLC);
        currMethod = new MethodScope(methodName);
        // todo check if returned index of method "end" includes last char
        Matcher endDeclareMatcher = endDeclarePattern.matcher(deceleration);
        if(!endDeclareMatcher.find())
            throw new ExceptionFileFormat( ILLEGAL_METHOD_DECELERATION);
        String slicedDeceleration = deceleration.substring(begDeclareMatcher.end() + 1, endDeclareMatcher.start()); //remove the "name part" of line
        argList = getArgsList(slicedDeceleration); // todo check which input the factory gets
        currMethod.argList = argList;
        methods.put(methodName, currMethod);
    }


}