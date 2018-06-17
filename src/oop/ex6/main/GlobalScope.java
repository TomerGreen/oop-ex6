
package oop.ex6.main;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalScope extends Scope {

    ///////////////////////CONSTANTS////////////////////////

    ////////////////////todo "more elegant" regex (now that line whitespaces are organized
    private static final String ILLEGAL_METHOD_DECELERATION = "illegal method deceleration";
    private static final int NAME_PLC = 3;
    private static final String METHODS_FIRST_PART_REGEX = "\\s*(void)(\\s+)(_*[a-zA-z]+\\w*)\\s*\\(\\s*";
    private static final String METHODS_SECOND_PART_REGEX = "\\)\\s*\\{\\s*";
    private static final String COMMA = ",";

    private HashMap<String, Method> methods;

    public GlobalScope(LineTree tree) {
        this.root = tree.root;
    }

    private void MethodDecAnalyezer(String deceleration) throws ExceptionFileFormat {
        String methodName;
        Method currMethod;
        ArrayList<Variable> argList;
        Pattern begDeclarePattern = Pattern.compile(METHODS_FIRST_PART_REGEX);
        Pattern endDeclarePattern = Pattern.compile(METHODS_SECOND_PART_REGEX);
        Matcher begDeclareMatcher = begDeclarePattern.matcher(deceleration);
        if(!begDeclareMatcher.find())
            throw new ExceptionFileFormat( ILLEGAL_METHOD_DECELERATION);
        methodName = begDeclareMatcher.group(NAME_PLC);
        currMethod = new Method(methodName);
        // todo check if returned index of method "end" includes last char
        Matcher endDeclareMatcher = endDeclarePattern.matcher(deceleration); 
        if(!endDeclareMatcher.find())
            throw new ExceptionFileFormat( ILLEGAL_METHOD_DECELERATION);
        String slicedDecleration = deceleration.substring(begDeclareMatcher.end() + 1, endDeclareMatcher.start()); //remove the "name part" of line
        argList = getArgsList(slicedDecleration); // todo check which input the factory gets
        currMethod.argList = argList;
    }



    // todo finish writing this method
    private ArrayList<Variable> getArgsList(String argsDeclare){
        ArrayList<Variable> argList = new ArrayList<>();
        String[] args = argsDeclare.split(COMMA);
        for(String arg : args){
            //argList.add(VaribleFactory.analyzeVariable(arg)); // todo change to explicit method call
        }
        return argList;
    }
}
