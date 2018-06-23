package oop.ex6.main;

/**
 * Thrown when there's a syntax error in the code.
 */
public class SyntaxException extends Exception {

    /**
     * Creates a new syntax exception object.
     * @param message The message to display.
     */
    public SyntaxException(String message) {
        super(message);
    }

    public static class ExceptionFileFormat extends SyntaxException {

        private final static String DEFAULT_MESSAGE = "file format is illegal";

        public ExceptionFileFormat(){
            super(DEFAULT_MESSAGE);
        }

        public ExceptionFileFormat(String message){
            super(message);
        }
    }
}
