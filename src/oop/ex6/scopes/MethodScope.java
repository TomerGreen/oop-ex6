package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.UnrecognizedVariableTypeException;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableParser;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MethodScope extends Scope {


    private static final String COMMA = ",";
    private static final String METHOD_DECELERATION_REGEX = "void "+ METHOD_NAME_REGEX + BRACKETS_CONTENTS + " ?\\{";
    private static final String ILLEGAL_METHOD_DECELERATION = "illegal method deceleration";
    private static final String ILLEGAL_METHOD_NAME = "method name already used";
    private static final int NAME_PLC = 1;
    private static final int ARGS_PLC = 2;
    private static final String EMPTY_STRING1 = "";
    private static final String EMPTY_STRING2 = " ";
    private Pattern methodDecelerationPattern = Pattern.compile(METHOD_DECELERATION_REGEX);

    private ArrayList<Variable> parameterList;

    MethodScope(LineNode root, Scope parent, GlobalScope globalScope) throws SyntaxException,
            LogicException{
        super(root, parent, globalScope);
        DecAnalyzer(root.getData());
    }

    /**
     * A method which verify and analyze the method's declaration
     * @param deceleration the declaration of the method
     * @throws SyntaxException when there's syntax problem with the Sjava code
     * @throws LogicException when there's logic problem with the Sjava code
     */
    private void DecAnalyzer(String deceleration) throws  SyntaxException, LogicException{
        Matcher methodDecelerationMatcher = methodDecelerationPattern.matcher(deceleration);
        if(!methodDecelerationMatcher.find())
            throw new SyntaxException.ExceptionFileFormat( ILLEGAL_METHOD_DECELERATION);
        String name = methodDecelerationMatcher.group(NAME_PLC);
        if(global.getMethods().containsKey(name)) {
            throw new SyntaxException.ExceptionFileFormat( ILLEGAL_METHOD_NAME);
        }
        parameterList = getParameterList(methodDecelerationMatcher.group(ARGS_PLC));
        global.setMethods(name, this);

    }

    /**
     * Returns a linked list of parameter variables based on a parameter list.
     * @param parameterList The content of the method declaration parentheses.
     * @return A linked list of parameter variables.
     * @throws SyntaxException When the parameter list syntax is wrong.
     * @throws LogicException When the parameter list is invalid.
     */
    private ArrayList<Variable> getParameterList(String parameterList) throws SyntaxException,
            LogicException{
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
                isFinal = ((finalMod != null) && finalMod.equals(FINAL));
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

    /**
     *
     * @param callingScope
     * @param parametersLine
     * @throws LogicException
     */
    void methodCallVerify(Scope callingScope, String parametersLine) throws LogicException{
        String[] parameters;
        if(parametersLine.equals(EMPTY_STRING1) | parametersLine.equals(EMPTY_STRING2))
            parameters = new String[0];
        else
            parameters = parametersLine.split(COMMA);
        if(parameters.length != parameterList.size())
            throw new IllegalMethodCallException();
        for(int i = 0; i < parameterList.size() ; i++){
            callingScope.verifyValueAssignment(parameterList.get(i), parameters[i].trim());
        }
    }

}