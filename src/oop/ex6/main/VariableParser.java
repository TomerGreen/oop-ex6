package oop.ex6.main;

<<<<<<< HEAD
=======
import java.util.regex.*;

/**
 * A factory class in charge of all creation and validation of variable declaration assignments.
 * All variable-related conventions are handled in this class and only in it. This class does not handle
 * validation relating to existing variable names.
 */
>>>>>>> e4657c340a3fa7a1d5de9ead34743be3ab6642d8
public class VariableParser {

    private static final String FINAL_VARIABLE_DECLARATION_PREFIX = "(?:(final) )?";
    private static final String VARIABLE_TYPES_REGEX = "((?:int)|(?:boolean)|(?:String)|(?:double)|(?:char)) ";
    private static final String LEGAL_VAR_NAME_REGEX = "(_*[a-zA-z]\\w*)";
    // Note that this regex matches ANY string as assigned value, using reluctant quantifier.
    private static final String VARIABLE_ASSIGNMENT_REGEX = " ?(=) ?(.*?)";
    // "a" or "a = 5"
    private static final String VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT = LEGAL_VAR_NAME_REGEX + "(?:"
            + VARIABLE_ASSIGNMENT_REGEX + ")?";
    private static final String VARIABLE_DECLARATION_REGEX = FINAL_VARIABLE_DECLARATION_PREFIX +
            VARIABLE_TYPES_REGEX + "(?:" + VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT + " ?, ?)*"
            + VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT + " ?;";

    /**
     * Returns whether the given value is a valid variable name.
     * Used by other classes to check whether an assignment is of a variable or a primitive value.
     * @param value The potential variable name to be checked.
     * @return Whether it is a valid variable name.
     */
    public static boolean isValidVarName(String value) {
        return value.matches(LEGAL_VAR_NAME_REGEX);
    }

    /* todo - helper method for testing. Delete when submitting. */
    public static boolean isVarDec(String value) {
        System.out.println("Regex is:");
        System.out.println(VARIABLE_DECLARATION_REGEX);
        return value.matches(VARIABLE_DECLARATION_REGEX);
    }
}
