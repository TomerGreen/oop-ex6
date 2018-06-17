package oop.ex6.main;

import java.util.LinkedList;
import java.util.regex.*;

/**
 * A factory class in charge of all creation and validation of variable declaration assignments.
 * All variable-related conventions are handled in this class and only in it. This class does not handle
 * validation relating to existing variable names.
 */
public class VariableParser {

    private static final String FINAL_VARIABLE_DECLARATION_PREFIX = "(?:(final) )?";
    private static final String VARIABLE_TYPES_REGEX = "((?:int)|(?:boolean)|(?:String)|(?:double)|(?:char)) ";
    // TODO: Consider following line instead (to minimize effort when creating new var type.
    //private static final String VARIABLE_TYPES_REGEX = "([a-zA-Z]+) ";
    private static final String LEGAL_VAR_NAME_REGEX = "(_*[a-zA-z]\\w*)";
    // Note that this regex matches ANY string as assigned value, using reluctant quantifier.
    private static final String VARIABLE_ASSIGNMENT_REGEX = " ?(=) ?(.*?)";
    // "a" or "a = 5"
    private static final String VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT = LEGAL_VAR_NAME_REGEX + "(?:"
            + VARIABLE_ASSIGNMENT_REGEX + ")?";
    private static final String VARIABLE_DECLARATION_REGEX = FINAL_VARIABLE_DECLARATION_PREFIX +
            VARIABLE_TYPES_REGEX + "(?:" + VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT + " ?, ?)*"
            + VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT + " ?;";

    public static Variable createVariable(String varName, String type, boolean isFinal)
            throws UnfamiliarVariableTypeException {
        if (type.equals("boolean")) {
            return new BooleanVariable(varName, isFinal);
        }
        else if (type.equals("int")) {
            return new IntVariable(varName,isFinal);
        }
        else if (type.equals("double")) {
            return new DoubleVariable(varName, isFinal);
        }
        else if (type.equals("char")) {
            return new CharVariable(varName, isFinal);
        }
        else if (type.equals("String")) {
            return new StringVariable(varName, isFinal);
        }
        else {
            throw new UnfamiliarVariableTypeException(type);
        }
    }

    /**
     * Returns whether the given value is a valid variable name.
     * Used by other classes to check whether an assignment is of a variable or a primitive value.
     * @param value The potential variable name to be checked.
     * @return Whether it is a valid variable name.
     */
    public static boolean isLegalVarName(String value) {
        return value.matches(LEGAL_VAR_NAME_REGEX);
    }

    /**
     * Returns a linked list of variable declaration line elements.
     * These elements are captured in the variable declaration regex.
     * @param varDecLine A variable declaration line.
     * @return A linked list of string tokens.
     * @throws SyntaxException If the line syntax does not match a variable declaration.
     */
    public static LinkedList<String> getTokenizedVarDeclaration(String varDecLine) throws SyntaxException {
        LinkedList<String> tokens = new LinkedList<>();
        Pattern varDecPattern = Pattern.compile(VARIABLE_DECLARATION_REGEX);
        Matcher varDecMatcher = varDecPattern.matcher(varDecLine);
        if (varDecMatcher.matches()) {
            for (int i=1; i<=varDecMatcher.groupCount(); i++) {
                tokens.add(varDecMatcher.group(i));
            }
        }
        else {
            throw new SyntaxException("Invalid variable declaration.");
        }
        return tokens;
    }

    /* todo - helper method for testing. Delete when submitting. */
    public static boolean isVarDec(String value) {
        System.out.println("Regex is: ");
        System.out.println(VARIABLE_DECLARATION_REGEX);
        Pattern pattern = Pattern.compile(VARIABLE_DECLARATION_REGEX);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            for (int i=1; i < matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
            return true;
        }
        return false;
    }


}
