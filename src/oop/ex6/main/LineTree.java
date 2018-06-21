package oop.ex6.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.objects.NativeString.trim; // todo better import


public class LineTree {

    ////////////////////////////////////////CONSTANTS////////////////////////////////////////////////////
    private final static String COMMENT_PREFIX_REGEX = "//";
    private final static String EMPTY_LINE = "";
    private final static String ROOT_LINE = "class;";
    private final static String SINGLE_LINE_SUFFIX_REGEX = ";";
    private final static String BEG_OF_SCOPE_REGEX = "( ?\\{)";
    private final static String END_OF_SCOPE_REGEX = "(\\})";

    private LineNode root;

    private static int lineNumber;

    public LineTree(BufferedReader br) throws IOException, ExceptionFileFormat {
        lineNumber = 0;
        root = parser(br, new LineNode(ROOT_LINE, null, lineNumber));
    }

    public LineNode getRoot() {
        return root;
    }

    /**
     * method which parse the sjava file to a tree of nested scopes which is easier to analyze.
     * @param br a BufferedReader of the original file we parse
     */
    private LineNode parser(BufferedReader br, LineNode currRoot) throws IOException, ExceptionFileFormat {
        String line;
        line = getLine(br);
        Pattern singleLinePattern = Pattern.compile(SINGLE_LINE_SUFFIX_REGEX);
        Pattern begOfScopePattern = Pattern.compile(BEG_OF_SCOPE_REGEX);
        while (line != null){
            // check that line isn't a comment or empty
            if(!line.startsWith(COMMENT_PREFIX_REGEX)){
                line = trim(line).replaceAll("\\s+"," ");
                if(!line.matches(EMPTY_LINE)){
                    Matcher singleLineMatcher = singleLinePattern.matcher(line);
                    Matcher begOfScopeMatcher = begOfScopePattern.matcher(line);
                    if(singleLineMatcher.find()) {
                        currRoot.addSon(new LineNode(line, currRoot, lineNumber));
                    }
                    else if(begOfScopeMatcher.find()) {
                        currRoot.addSon(parser(br, new LineNode(line, currRoot, lineNumber)));
                    }
                    else if(line.matches(END_OF_SCOPE_REGEX) && currRoot.getParent() != null) {
                        return currRoot;
                    }
                    else {
                        throw new ExceptionFileFormat();
                    }
                }
            }
            line = getLine(br);
        }
        if(currRoot.getData().equals(ROOT_LINE)){ // check that Scope brackets are balanced
            return currRoot;
        }
        else {
            throw new ExceptionFileFormat();
        }
    }

    private String getLine(BufferedReader br) throws IOException {
        lineNumber++;
        String line = br.readLine();
        if(line == null)
            return null;
        else
            return line;
    }

}
