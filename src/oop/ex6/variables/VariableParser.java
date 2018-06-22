package oop.ex6.variables;

import oop.ex6.main.SyntaxException;
import oop.ex6.main.UnrecognizedVariableTypeException;

import java.util.LinkedList;
import java.util.regex.*;

/**
 * A factory class in charge of all creation and validation of variable declaration assignments.
 * All variable-related conventions are handled in this class and only in it. This class does not handle
 * validation relating to existing variable names.
 */
public class VariableParser {

    private static final String DECLARATION_SEPARATOR = ",";
    private static final String FINAL_VARIABLE_DECLARATION_PREFIX = "(?:(final) )?";
    private static final String VARIABLE_TYPES_REGEX = "((?:int)|(?:boolean)|(?:String)|(?:double)|(?:char)) ";
    // TODO: Consider following line instead (to minimize effort when creating new var type.
    //private static final String VARIABLE_TYPES_REGEX = "([a-zA-Z]+) ";
    private static final String LEGAL_VAR_NAME_REGEX = "(_*[a-zA-z]\\w*)";
    // Note that this regex matches ANY string as assigned value, using reluctant quantifier.
    private static final String ASSIGNMENT_WITHOUT_VAR_NAME = " ?(=) ?(.*?)";
    private static final String LEGAL_VARIABLE_ASSIGNMENT = LEGAL_VAR_NAME_REGEX + ASSIGNMENT_WITHOUT_VAR_NAME + " ?;";
    // "a" or "a=5" or " a = 5 "
    private static final String VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT = " ?" + LEGAL_VAR_NAME_REGEX + "(?:"
            + ASSIGNMENT_WITHOUT_VAR_NAME + ")? ?";
    // Matches an entire variable declaration line. Note that group(3) is the comma-separated
    // list of variables names and possible assignments.
    private static final String VARIABLE_DECLARATION_REGEX = FINAL_VARIABLE_DECLARATION_PREFIX +
            VARIABLE_TYPES_REGEX + "((?:" + VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT + ",)*"
            + VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT + ");";
    private static final String LEGAL_METHOD_PARAMETER = " ?" + FINAL_VARIABLE_DECLARATION_PREFIX
            + VARIABLE_TYPES_REGEX + LEGAL_VAR_NAME_REGEX + " ?";
    private static final String EMPTY_STRING = " ?";

    /**
     * A factory method that creates a variable based on a declared type, name, and final modifier.
     * @param varName The variable name.
     * @param type The variable type.
     * @param isFinal Whether it should be final.
     * @return A variable object.
     * @throws UnrecognizedVariableTypeException When the type name is unrecognized.
     */
    public static Variable createVariable(String varName, String type, boolean isFinal)
            throws UnrecognizedVariableTypeException {
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
            throw new UnrecognizedVariableTypeException(type);
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
     * Checks if a line matches the variable declaration pattern.
     * @param line The potentially var dec line.
     * @return Whether it is a legal variable declaration.
     */
    public static boolean isLegalVarDec(String line) { return line.matches(VARIABLE_DECLARATION_REGEX); }

    /**
     * Checks if a line matches the variable assignment pattern.
     * @param line The potential variable assignment line.
     * @return Whether it is a legal variable assignment.
     */
    public static boolean isLegalAssignment(String line) { return line.matches(LEGAL_VARIABLE_ASSIGNMENT); }

    /**
     * Returns a linked list of variable declaration line elements.
     * These elements are captured in the variable declaration regex.
     * @param varDecLine A variable declaration line.
     * @return A linked list of string tokens.
     * @throws SyntaxException If the line syntax does not match a variable declaration.
     */
    public static LinkedList<String> getTokenizedVarDeclaration(String varDecLine) throws SyntaxException {
        LinkedList<String> tokens = new LinkedList<>();
        Pattern varDecLinePattern = Pattern.compile(VARIABLE_DECLARATION_REGEX);
        Matcher varDecLineMatcher = varDecLinePattern.matcher(varDecLine);
        Pattern varDecPattern = Pattern.compile(VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT);
        Matcher varDecMatcher;
        if (varDecLineMatcher.matches()) {
            tokens.add(varDecLineMatcher.group(1)); // A "final" or null token.
            tokens.add(varDecLineMatcher.group(2)); // A variable type.
            // This is an array of individual var declarations.
            String[] varDecs = varDecLineMatcher.group(3).split(DECLARATION_SEPARATOR);
            for (String declaration : varDecs) {
                varDecMatcher = varDecPattern.matcher(declaration);
                if (varDecMatcher.matches()) {
                    tokens.add(varDecMatcher.group(1));  // The var name.
                    tokens.add(varDecMatcher.group(2));  // = or null.
                    tokens.add(varDecMatcher.group(3));  // Assigned value or null.
                }
                else {
                    throw new SyntaxException("Variable declaration \"" + declaration + "\" is invalid");
                }
            }
        }
        else {
            throw new SyntaxException("Invalid variable declaration.");
        }
        return tokens;
    }

    /**
     * Returns a linked list of variable types and names from a method parameter list.
     * @param paramList The parameter list, contained in parentheses.
     * @return A linked list of parameter types and variable names.
     * @throws SyntaxException When the parameter list is not in the right format.
     */
    public static LinkedList<String> getTokenizedParameterList(String paramList) throws SyntaxException {
        LinkedList<String> tokens = new LinkedList<>();
        Pattern paramPattern = Pattern.compile(LEGAL_METHOD_PARAMETER);
        String[] params = paramList.split(DECLARATION_SEPARATOR);
        for (String param : params) {
            if(param.matches(EMPTY_STRING) && params.length == 1) {
                break;
            }
                Matcher paramMatcher = paramPattern.matcher(param);
                if (paramMatcher.matches()) {
                    tokens.add(paramMatcher.group(1));  // "final" or null.
                    tokens.add(paramMatcher.group(2));  // The parameter type.
                    tokens.add(paramMatcher.group(3));  // The parameter name.
                }
                else {
                    throw new SyntaxException("Illegal parameter syntax \"" + param + "\".");
                }

        }
        return tokens;
    }

    /**
     * Parses an assignment line into an assignment object.
     * @param line The potential assignment line.
     * @return An array of two tokens.
     * @throws SyntaxException When the assignment line is malformed.
     */
    public static VariableAssignment getAssignment(String line) throws SyntaxException {
        final int ASSIGNEE_GROUP = 1;
        final int VALUE_GROUP = 3;
        VariableAssignment assignment;
        Pattern assignPattern = Pattern.compile(LEGAL_VARIABLE_ASSIGNMENT);
        Matcher assignMatcher = assignPattern.matcher(line);
        if (assignMatcher.matches()) {
            assignment = new VariableAssignment(assignMatcher.group(ASSIGNEE_GROUP), assignMatcher.group(VALUE_GROUP));
        }
        else {
            throw new SyntaxException("Illegal assignment syntax \"" + line + "\"." );
        }
        return assignment;
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
