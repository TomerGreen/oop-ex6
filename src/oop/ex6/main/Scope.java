package oop.ex6.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;

public abstract class Scope {

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
     * searching several variable tables). The only requirement for the returned variable is that it is ASSIGNABLE.
     * @param name The name of the variable to be searched.
     * @return The corresponding variable object.
     * @throws UnknownVariableException If the variable is not found in the local table, and the parent scope is null.
     */
    protected Variable getDefinedVariable(String name) throws UnknownVariableException {
        if (this.variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parent == null) {
            throw new UnknownVariableException("Variable name \"" + name + "\" is undefined.");
        }
        return parent.getDefinedVariable(name);
    }

    // TODO not sure if this method is necessary.
    /**
     * Returns whether the given variable name represents a defined and initialized variable.
     * A usable variable name can be used as a method argument, or assigned to another variable.
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
     * @param varName The given var name.
     * @return Whether it is definable.
     */
    protected boolean isVarnameDeclarable(String varName) {
        return !this.variables.containsKey(varName);
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

    // TODO consider this method.
    /*
     * Initializes a variable if the assignment is valid.
     * @param var The variable to initialize
     * @param value The assigned value.
     * @throws UnknownVariableException If the assigned value is an undefined varname.
     * @throws InvalidAssignmentException If the assignment is otherwise invalid.

    private void assignValue(Variable var, String value) throws InvalidAssignmentException,
            UnitializedVariableUsageException, UnknownVariableException {
        if (isValidAssignment(var, value)) {
            var.initialize();
        }
    }
    */

    private void parseVarDeclaration(String varDecLine) throws SyntaxException, UnfamiliarVariableTypeException,
            VariableDeclarationException {
        String currToken;  // The current token.
        boolean isFinal = false;  // Whether the declared variables are final.
        String type;  // The type name of the declared variables.
        Variable currVar;  // The current variable object being parsed.
        String currVarName;  // The name of the current variable being parsed.
        Iterator<String> tokenIterator = VariableParser.getTokenizedVarDeclaration(varDecLine).iterator();
        currToken = tokenIterator.next();  // Either final or null.
        if (currToken != null && currToken.equals("final")) {
            isFinal = true;
        }
        // next is type name.
        type = tokenIterator.next();
        currToken = tokenIterator.next();  // Must be a var name.
        while (currToken != null) {  // In each iteration the current token is either a var name or null.
            currVarName = currToken;
            if (isVarnameDeclarable(currVarName)) {
                currVar = VariableParser.createVariable(currToken, type, isFinal);
            }
            else {
                throw new VariableDeclarationException(currVarName);
            }
            // At this point the variable name is declarable.
            currVar = VariableParser.createVariable(currToken, type, isFinal);

            
            tokenAfterName = tokenIterator.next();  // current token is either "=" or null.
            if (tokenAfterName.equals("=")) {
                currVar.initialize();
            }
            assignee = tokenIterator.next();  // Current token is an assigned value or null.
        }
    }

    /**
     * @param line
     * @return
     */
    private Variable[] parseVariableDec(String line) {
        return null;
    }

}
