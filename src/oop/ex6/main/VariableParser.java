package oop.ex6.main;

import java.util.regex.*;

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
            + VAR_DEC_WITH_OR_WITHOUT_ASSIGNMENT + " ;";

    /**
     * Returns whether the given value is a valid variable name.
     * Used by other classes to check whether an assignment is of a variable or a primitive value.
     * @param value The potential variable name to be checked.
     * @return Whether it is a valid variable name.
     */
    public static boolean isValidVarName(String value) {
        return value.matches(LEGAL_VAR_NAME_REGEX);
    }
}
