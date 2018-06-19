package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableParser;

import java.util.Iterator;
import java.util.ArrayList;
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
//        argList = getParameterList(methodDecelerationMatcher.group(ARGS_PLC)); // todo check which method to call
    }

    /**
     * Returns a linked list of parameter variables based on a parameter list.
     * @param parameterList The content of the method declaration parentheses.
     * @return A linked list of parameter variables.
     * @throws SyntaxException When the parameter list syntax is wrong.
     * @throws InvalidParameterListException When the parameter list is invalid.
     */
    private ArrayList<Variable> getParameterList(String parameterList) throws SyntaxException,
            InvalidParameterListException {
        ArrayList<Variable> parameters = new ArrayList<>();
        String finalMod;  // The current token.
        boolean isFinal;  // Whether the declared parameter is final.
        String type;  // The type name of the declared parameter.
        Variable currParam;  // The current variable object being parsed.
        String paramName;  // The name of the current variable being parsed.
        try {
            Iterator<String> tokenIterator = VariableParser.getTokenizedParameterList(parameterList).iterator();
            while (tokenIterator.hasNext()) {
                finalMod = tokenIterator.next();  // A "final" modifier or null.
                if (finalMod.equals(FINAL)) {
                    isFinal = true;
                } else {
                    isFinal = false;
                }
                type = tokenIterator.next();  // A variable type.
                paramName = tokenIterator.next();  // A parameter name.
                if (isVarnameDeclarable(paramName)) {
                    currParam = VariableParser.createVariable(paramName, type, isFinal);
                }
                else {
                    throw new InvalidParameterListException("Parameter name "
                            + paramName + " is already declared.");
                }
                currParam.initialize();  // A parameter variable is always initialized (assignable in method scope).
                addLocalVariable(currParam);
                parameters.add(currParam);
            }
        }
        catch (UnrecognizedVariableTypeException e) {
            throw new InvalidParameterListException(e.getMessage(), e);
        }
        return parameters;
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