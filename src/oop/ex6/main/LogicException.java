package oop.ex6.main;

import oop.ex6.variables.Variable;

/**
 * Thrown when there's a logical error in the program code.
 */
public abstract class LogicException extends Exception {

    /**
     * Creates a new exception object with the given message.
     * @param message The message.
     */
    protected LogicException(String message) {
        super(message);
    }

    /**
     * A constructor with a cause variable.
     * @param message The error message.
     * @param cause The cause exception.
     */
    protected LogicException(String message, Exception cause) {
        super(message, cause);
    }

    public static class UnrecognizedVariableTypeException extends LogicException {

        public UnrecognizedVariableTypeException(String type) {
            super("Unfamiliar variable type '" + type + "'.");
        }
    }

    /**
     * Thrown when something is wrong with a variable assignment.
     */
    public static class InvalidAssignmentException extends LogicException {

        /**
         * Creates a new exception object with the given message.
         *
         * @param message The message.
         */
        public InvalidAssignmentException(String message) {
            super(message);
        }

        /**
         * A constructor with cause.
         * @param message The message.
         */
        public InvalidAssignmentException(String message, Exception cause) {
            super(message, cause);
        }
    }

    /**
     * Thrown when an invalid declaration is encountered.
     */
    public static class InvalidVariableDeclarationException extends LogicException {

        /**
         * Creates a new exception.
         * @param message The exception message.
         */
        public InvalidVariableDeclarationException(String message, Exception cause) {
            super(message, cause);
        }

        /**
         * A constructor with no cause.
         * @param message The exception message.
         */
        public InvalidVariableDeclarationException(String message) {
            super(message);
        }
    }

    public static class NoReturnException extends LogicException{


        public NoReturnException(String message) { super(message); }
    }

    /**
     * Thrown when an uninitialized variable is used.
     */
    public static class UninitializedVariableUsageException extends LogicException {

        /**
         * Creates a new exception.
         * @param var The uninitialized variable.
         */
        public UninitializedVariableUsageException(Variable var) {
            super("Cannot use uninitialized variable " + var.getName());
        }
    }

    /**
     * Thrown when the parameter list is invalid.
     */
    public static class InvalidParameterListException extends LogicException{

        /**
         * Constructor with cause.
         */
        public InvalidParameterListException(String message, Exception cause) {
            super(message, cause);
        }

        /**
         * A rebel without a cause.
         */
        public InvalidParameterListException(String message) {
            super(message);
        }
    }

    /**
     * Thrown when an undefined method name is referred.
     */
    public static class UnfamiliarMethodName extends LogicException{

        private static final String DEFAULT_MSG = "Unfamiliar Method Name";

        public UnfamiliarMethodName() {
            super(DEFAULT_MSG);
        }

    }
}
