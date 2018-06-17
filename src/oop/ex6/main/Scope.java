package oop.ex6.main;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Scope {
    
    ////////////////////////////////////CONSTANTS////////////////////////////;
    private static final String OPEN_BRACKET = "{";
    protected static final String RETURN = "return";
    protected static final String METHOD_NAME_REGEX = "([a-zA-z]+\\w*)";
    private static final String METHOD_CALL_REGEX = METHOD_NAME_REGEX + " ?\\(";
    protected static final String METHODS_SECOND_PART_REGEX = "\\) ?\\{";
    
    /** The root of the line tree that represents this scope. */
    protected LineNode root;

    /** The variables that were defined locally in the statement. */
    protected HashMap<String, Variable> variables;

    /** The statement whose scope encapsulates this statement. */
    protected Scope parent;

    /** The global statement this statement is a part of. */
    GlobalScope global;

    protected Scope(){}

    protected Scope(LineNode root, Scope parent){
        this.root = root;
        this.parent = parent;
        variables = new HashMap<>();
    }


    /**
     * A recursive method that looks for a variable with a given name and returns it.
     * Since it is recursive, it allows for a different implementation in ancestor scopes (for example,
     * searching several variable tables).
     * @param name The name of the variable to be searched.
     * @return The corresponding variable object.
     * @throws UnknownVariableException If the variable is not found in the local table, and the parent scope is null.
     */
    private Variable getDefinedVariable(String name) throws UnknownVariableException {
        if (this.variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parent == null) {
            throw new UnknownVariableException("Variable name \"" + name + "\" is undefined.");
        }
        return parent.getDefinedVariable(name);
    }

    /**
     * Checks if the assignment of the given value or variable to the given variable is legal.
     * @param var The variable object to which we want to assign.
     * @param value The value or name of the variable to be assigned.
     * @return Whether the assignment is valid.
     * @throws InvalidAssignmentException If the assignment is invalid for
     */
    private boolean isValidAssignment(Variable var, String value) throws InvalidAssignmentException,
            UnknownVariableException, UnitializedVariableUsageException {
        if (var.isFinal() && var.isInitialized()) {
            throw new InvalidAssignmentException("Cannot assign value to final variable after declaration.");
        }
        // The assigned value is a variable.
        else if (VariableParser.isLegalVarName(value)) {
            Variable assignee = getDefinedVariable(value);  // Throws exception if assignee wasn't returned.
            if (assignee.isInitialized()) {
                // todo TEST!!!
                if (var.getClass().isInstance(assignee)) {
                    return true;
                }
                // Trying to assign var with invalid type.
                else {
                    throw new InvalidAssignmentException("Cannot assign variable of type '" + assignee.getTypeName()
                            + "' to variable of type '" + var.getTypeName() + "'.");
                }
            }
            // Trying to assign uninitialized variable.
            else {
                throw new UnitializedVariableUsageException(assignee);
            }
        }
        // The assigned value is primitive, i.e "hello" , '@' , 5. , 3.2 etc.
        else {
            if (var.isValidValue(value)) {
                return true;
            }
            else {
                throw new InvalidAssignmentException("Cannot assign value " + value + " to variable of type "
                        + var.getTypeName() + ".");
            }
        }
    }

    /**
     * Initializes a variable if the assignment is valid.
     * @param var The variable to initialize
     * @param value The assigned value.
     * @throws UnknownVariableException If the assigned value is an undefined varname.
     * @throws InvalidAssignmentException If the assignment is otherwise invalid.
     */
    private void assignValue(Variable var, String value) throws InvalidAssignmentException,
            UnitializedVariableUsageException, UnknownVariableException {
        if (isValidAssignment(var, value)) {
            var.initialize();
        }
    }

    private void parseVarDeclaration(String line) {
        //todo
    }

    /**
     * @param line
     * @return
     */
    private Variable[] parseVariableDec(String line) {
        return null;
    }
        //todo

    void verifyScope(){
        Pattern begLinePattern = Pattern.compile(METHOD_CALL_REGEX);
        Pattern endLinePattern = Pattern.compile(METHODS_SECOND_PART_REGEX);
        for (LineNode son : root.sons) {
            String openingLine = son.data;
            Matcher begLineMatcher = begLinePattern.matcher(openingLine);
            Matcher endLineMatcher = endLinePattern.matcher(openingLine);
            if(openingLine.endsWith(OPEN_BRACKET))
                new ConditionScope(son, this);
            else if(openingLine.matches(RETURN)){ } //important (end of method) return line are considered previously
            else if(begLineMatcher.find() && endLineMatcher.find()){
                methodCallVerify(this, openingLine);
            }
            //todo varAssignAnalyzer(line) and varDecAnalyzer(line) which update the var table
        }
    }

    void methodCallVerify(Scope callingScope, String line){

    }
}
