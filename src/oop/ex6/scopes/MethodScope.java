package oop.ex6.scopes;

import oop.ex6.main.ExceptionFileFormat;
import oop.ex6.main.InvalidAssignmentException;
import oop.ex6.main.LineNode;
import oop.ex6.main.UninitializedVariableUsageException;
import oop.ex6.variables.Variable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodScope extends Scope {


    //todo change to whole deceleration
    private static final String COMMA = ",";
    private static final String METHOD_DECELERATION_REGEX = "void "+ METHOD_NAME_REGEX + BRACKETS_CONTENTS + " ?\\{";
    private static final String ILLEGAL_METHOD_DECELERATION = "illegal method deceleration";
    private static final String ILLEGAL_METHOD_NAME = "method name already used";
    private static final int NAME_PLC = 0;
    private static final int ARGS_PLC = 1;

    String name;

    Variable[] argList;

    public MethodScope(LineNode root, Scope parent, GlobalScope globalScope) throws ExceptionFileFormat {
        super(root, parent, globalScope);
        DecAnalyzer(root.getData());
    }

    private void DecAnalyzer(String deceleration) throws ExceptionFileFormat {
        Pattern methodDecelerationPattern = Pattern.compile(METHOD_DECELERATION_REGEX);
        Matcher methodDecelerationMatcher = methodDecelerationPattern.matcher(deceleration);
        if(!methodDecelerationMatcher.find())
            throw new ExceptionFileFormat( ILLEGAL_METHOD_DECELERATION);
        String tempName = methodDecelerationMatcher.group(NAME_PLC);
        if(global.getMethods().containsKey(tempName))
            throw new ExceptionFileFormat( ILLEGAL_METHOD_NAME);
//        argList = getArgsList(methodDecelerationMatcher.group(ARGS_PLC)); // todo check which method to call
    }


    // todo send to method of variable class or finish writing this method
//    private ArrayList<Variable> getArgsList(String argsDeclare){
//        ArrayList<Variable> argList = new ArrayList<>();
//        String[] args = argsDeclare.split(COMMA);
//        for(String arg : args){
//            //argList.add(VaribleFactory.analyzeVariable(arg)); // todo change to explicit method call
//        }
//        return argList;
//    }
    void methodCallVerify(Scope callingScope, String parametersLine) throws IllegalMethodCallException,
            InvalidAssignmentException, UninitializedVariableUsageException {
        String[] parameters = parametersLine.split(COMMA);
        if(parameters.length != argList.length)
            throw new IllegalMethodCallException();
        for(int i = 0; i < argList.length ; i++){
            callingScope.verifyValueAssignment(argList[i], parameters[i].trim());
        }

    }


}