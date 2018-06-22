package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableAssignment;
import oop.ex6.variables.VariableParser;
import oop.ex6.main.UnfamiliarMethodName;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Scope {

    ////////////////////////////////////CONSTANTS////////////////////////////;
    static final String RETURN = "return ?;";
    static final String FINAL = "final";
    static final String METHOD_NAME_REGEX = "([a-zA-z]+\\w*)";
    private static final String CONDITION_TYPES_REGEX = "((?:while)|(?:if))";
    static final String BRACKETS_CONTENTS = " ?\\((.*?)\\) ?";
    private static final String CONDITION_SCOPE_DEC_REGEX = CONDITION_TYPES_REGEX + BRACKETS_CONTENTS + "\\{";
    private static final String METHOD_CALL_REGEX = METHOD_NAME_REGEX + BRACKETS_CONTENTS + ";";


    /**
     * The root of the line tree that represents this scope.
     */
    protected LineNode root;

    /**
     * The variables that were defined locally in the statement.
     */
    protected HashMap<String, Variable> variables;

    /**
     * The statement whose scope encapsulates this statement.
     */
    private Scope parent;

    /**
     * The global statement this statement is a part of.
     */
    GlobalScope global;

    Scope(LineNode root, Scope parent, GlobalScope global) {
        this.global = global;
        this.root = root;
        this.parent = parent;
        variables = new HashMap<>();
    }


    /**
     * A recursive method that looks for a variable with a given name and returns it.
     * Since it is recursive, it allows for a different implementation in ancestor scopes (for example,
     * searching several variable tables). The only requirement for the returned variable is that it is ASSIGNABLE.
     *
     * @param name The name of the variable to be searched.
     * @return The corresponding variable object.
     * @throws UnknownVariableException If the variable is not found in the local table, and the parent scope is null.
     */
    protected Variable getDefinedVariable(String name) throws UnknownVariableException {
        if (this.variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parent == null) {
            return global.getDefinedVariable(name);
        }
        return parent.getDefinedVariable(name);
    }

    /**
     * Adds a variable to the local symbol table.
     *
     * @param var The variable to be added.
     */
    protected void addLocalVariable(Variable var) {
        variables.put(var.getName(), var);
    }

    // TODO not sure if this method is necessary.

    /**
     * Returns whether the given variable name represents a defined and initialized variable.
     * A usable variable name can be used as a method argument, or assigned to another variable.
     *
     * @param varName The given variable name.
     * @return Whether it is usable.
     * @throws UnknownVariableException If the variable is not defined (unassignable).
     */
    protected boolean isVarnameUsable(String varName) throws UnknownVariableException {
        Variable var = getDefinedVariable(varName);
        return var.isInitialized();
    }

    /**
     * Whether a given variable name may be declared as a new variable.
     *
     * @param varName The given var name.
     * @return Whether it is definable.
     */
    protected boolean isVarnameDeclarable(String varName) {
        return !this.variables.containsKey(varName);
    }

    /**
     * Checks if the assignment of the given value or variable to the given variable is legal.
     *
     * @param var   The variable object to which we want to assign.
     * @param value The value or name of the variable to be assigned.
     * @throws UninitializedVariableUsageException When trying to assign uninitialized variable.
     * @throws InvalidAssignmentException          If the assignment is invalid for any other reason.
     */
    protected void verifyValueAssignment(Variable var, String value) throws InvalidAssignmentException,
            UninitializedVariableUsageException {
        if (var.isFinal() && var.isInitialized()) {
            throw new InvalidAssignmentException("Cannot assign value to final variable after declaration.");
        }
        // Checks if the value is a valid assignment of a primitive value.
        else if (var.isValidValue(value)) {
                return;
            }
        // Checks if the assigned value is a variable name of a usable variable.
        else if (VariableParser.isLegalVarName(value)) {
            Variable assignee;  // Throws exception if assignee wasn't returned.
            try {
                assignee = getDefinedVariable(value);
            } catch (UnknownVariableException e) {
                throw new InvalidAssignmentException(e.getMessage(), e);
            }
            if (assignee.isInitialized()) {
                // todo TEST!!!
                if (var.getClass().isInstance(assignee)) {
                    return;
                }
                // Trying to assign var with invalid type.
                else {
                    throw new InvalidAssignmentException("Cannot assign variable of type '" + assignee.getTypeName()
                            + "' to variable of type '" + var.getTypeName() + "'.");
                }
            }
            // Trying to assign uninitialized variable.
            else {
                throw new UninitializedVariableUsageException(assignee);
            }
        }
        else {
            throw new InvalidAssignmentException("Cannot assign value " + value + " to variable of type "
                    + var.getTypeName() + ".");
        }
    }

    // TODO shouldn't be public.

    /**
     * Receives a potentially valid variable declaration line, and creates the appropriate variables in the
     * scope's symbol table.
     *
     * @param varDecLine The variable declaration line.
     * @throws InvalidVariableDeclarationException When anything is wrong in the declaration.
     */
    public void parseVarDeclaration(String varDecLine) throws InvalidVariableDeclarationException {
        String currToken;  // The current token.
        boolean isFinal = false;  // Whether the declared variables are final.
        String type;  // The type name of the declared variables.
        Variable currVar;  // The current variable object being parsed.
        String currVarName;  // The name of the current variable being parsed.
        try {
            Iterator<String> tokenIterator = VariableParser.getTokenizedVarDeclaration(varDecLine).iterator();
            currToken = tokenIterator.next();  // Either final or null.
            if (currToken != null && currToken.equals(FINAL)) {
                isFinal = true;
            }
            // next is type name.
            type = tokenIterator.next();
            while (tokenIterator.hasNext()) {  // In each iteration the current token is either a var name or null.
                currToken = tokenIterator.next();  // Must be a var name.
                currVarName = currToken;
                if (isVarnameDeclarable(currVarName)) {
                    currVar = VariableParser.createVariable(currToken, type, isFinal);
                } else {
                    throw new InvalidVariableDeclarationException("Cannot override declared variable '"
                            + currVarName + "'.");
                }
                // At this point the variable name is declarable.
                currToken = tokenIterator.next();  // Current token is "=" or null.
                if (currToken != null && currToken.equals("=")) {
                    currToken = tokenIterator.next();  // Current token is an assigned value.
                    verifyValueAssignment(currVar, currToken);  // At this point the declared variable is uninitialized.
                    currVar.initialize();  // Initializing variable.
                }
                // No assignment. A null token is expected, but this is verified by regex.
                else {
                    tokenIterator.next();  // null.
                }
                if (currVar.isFinal() && !currVar.isInitialized()) {
                    throw new InvalidVariableDeclarationException("Variable '" + currVarName
                            + "' is declared final but is not initialized.");
                }
                addLocalVariable(currVar);
            }
        } catch (SyntaxException | UnrecognizedVariableTypeException | InvalidAssignmentException
                | UninitializedVariableUsageException e) {
            throw new InvalidVariableDeclarationException(e.getMessage(), e);
        }
    }

    /**
     * Checks if an assignment line is valid.
     * @param assignLine The potential assignment line to be parsed.
     * @throws SyntaxException If the line is malformed.
     * @throws UnknownVariableException If the target variable name is not declared.
     * @throws InvalidAssignmentException If the assignment is wrong for any other reason.
     * @throws UninitializedVariableUsageException If the assigned value is a variable name of an uninitialized.
     */
    protected void parseAssignment(String assignLine) throws SyntaxException, UnknownVariableException,
            InvalidAssignmentException, UninitializedVariableUsageException {
        VariableAssignment assignment = VariableParser.getAssignment(assignLine);
        Variable target = getDefinedVariable(assignment.getTarget());
        verifyValueAssignment(target, assignment.getValue());
        target.initialize();
    }
    protected void verifyScope() throws InvalidVariableDeclarationException, ScopeException{
        Pattern conditionScopeDecPattern = Pattern.compile(CONDITION_SCOPE_DEC_REGEX);
        Pattern methodCallPattern = Pattern.compile(METHOD_CALL_REGEX);
        try {
            for (LineNode son : root.getSons()) {
                String openingLine = son.getData();
                Matcher methodCallMatcher = methodCallPattern.matcher(openingLine);
                Matcher conditionScopeMatcher = conditionScopeDecPattern.matcher(openingLine);
                if (conditionScopeMatcher.find()) {
                    String conditionPart = conditionScopeMatcher.group(2);
                    new ConditionScope(son, this, conditionPart, global);
                } else if(openingLine.matches(RETURN)) { } //method's last return line was considered previously
                else if (methodCallMatcher.find()) {
                    String methodName = methodCallMatcher.group(1);
                    String argsPart = methodCallMatcher.group(2);
                    if (global.getMethods().containsKey(methodName))
                        global.getMethods().get(methodName).methodCallVerify(this, argsPart);
                    else {
                        throw new UnfamiliarMethodName();
                    }
                } else if (VariableParser.isLegalVarDec(openingLine))
                    parseVarDeclaration(openingLine);
                else if (VariableParser.isLegalAssignment(openingLine))
                    parseAssignment(openingLine);
                else {
                    throw new ExceptionFileFormat();
                }
            }
        } catch (UnrecognizedVariableTypeException | InvalidAssignmentException
                | UninitializedVariableUsageException|SyntaxException | UnknownVariableException  e) {
            throw new InvalidVariableDeclarationException(e.getMessage(), e);
        }catch (IllegalMethodCallException | UnfamiliarMethodName e){
            throw new ScopeException(e.getMessage(), e);
        }


    }
}
