package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableParser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodScope extends Scope {


    //todo change to whole deceleration
//    private static final String OPEN_BRACKET_REGEX = "\\) ?\\{";
//    private static final String METHODS_FIRST_PART_REGEX = "(void) " + METHOD_NAME_REGEX +" ?\\( ?";
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

    private Variable[] getParameterList(String parameterList) throws InvalidParameterListException {
        String currToken;  // The current token.
        boolean isFinal;  // Whether the declared parameter is final.
        String type;  // The type name of the declared parameter.
        Variable currParam;  // The current variable object being parsed.
        String paramName;  // The name of the current variable being parsed.
        try {
            Iterator<String> tokenIterator = VariableParser.getTokenizedParameterList(parameterList).iterator();
            while (tokenIterator.hasNext()) {
                currToken = tokenIterator.next();  // A "final" modifier or null.
                if (currToken.equals(FINAL)) {
                    isFinal = true;
                } else {
                    isFinal = false;
                }
                type = tokenIterator.next();  // A variable type.
                paramName = tokenIterator.next();  // A parameter name.
                if (isVarnameDeclarable(paramName)) {
                    currParam = VariableParser.createVariable(paramName, type, isFinal);
                } else {
                    throw new InvalidVariableDeclarationException("Parameter name " + paramName + " is already declared.");
                }
            }
        }
        catch (SyntaxException e) {
            throw new InvalidParameterListException(e.getMessage(), e);
        }
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
    void methodCallVerify(Scope callingScope, String args){
    }


}