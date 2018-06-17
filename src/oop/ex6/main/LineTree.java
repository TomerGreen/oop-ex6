package oop.ex6.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineTree {

    ////////////////////////////////////////CONSTANTS////////////////////////////////////////////////////

    private final static String COMMENT_PREFIX_REGEX = "(\\s*\\//\\s*)"; //todo check if can be spaces before comma
    private final static String EMPTY_LINE_REGEX = "(\\s*)";
    private final static String ROOT_LINE = "class;";
    private final static String SINGLE_LINE_SUFFIX_REGEX = "(\\s*\\;\\s*)";
    private final static String BEG_OF_SCOPE_REGEX = "(\\s*\\{\\s*)";
    private final static String END_OF_SCOPE_REGEX = "(\\s*\\}\\s*)";

    ScopeNode root;

    public LineTree(BufferedReader br) throws IOException, ExceptionFileFormat {
        root = parser(br, new ScopeNode(ROOT_LINE, null));
    }

    /**
     * method which parse the sjava file to a tree of nested scopes which is easier to analyze.
     * @param br a BufferedReader of the original file we parse
     */
    private ScopeNode parser(BufferedReader br, ScopeNode currRoot) throws IOException, ExceptionFileFormat {
        String line;
        line = br.readLine();
        Pattern begOfCommentPattern = Pattern.compile(COMMENT_PREFIX_REGEX);
        Pattern singleLinePattern = Pattern.compile(SINGLE_LINE_SUFFIX_REGEX);
        Pattern begOfScopePattern = Pattern.compile(BEG_OF_SCOPE_REGEX);
        while (line != null){
            Matcher begOfCommentMatcher = begOfCommentPattern.matcher(line);
            Matcher singleLineMatcher = singleLinePattern.matcher(line); //todo think of transfer after the condition
            Matcher begOfScopeMatcher = begOfScopePattern.matcher(line); //todo think of transfer after the condition
            if(!(line.matches(EMPTY_LINE_REGEX)|| begOfCommentMatcher.find())){ // check that line isn't a comment or empty
                if(singleLineMatcher.find()) {
                    currRoot.addSon(new ScopeNode(line, currRoot));
                }
                else if(begOfScopeMatcher.find()) {
                    currRoot.addSon(parser(br, new ScopeNode(line, currRoot)));
                }
                else if(line.matches(END_OF_SCOPE_REGEX) && currRoot.parent != null)
                    currRoot = currRoot.parent;
                else
                    throw new ExceptionFileFormat();
            }
            line = br.readLine();
        }
        if(currRoot.getData().equals(ROOT_LINE)){ // check that Scope brackets are balanced
            return currRoot;
        }
        else {
            throw new ExceptionFileFormat();
        }
    }

    //todo think of adding "scope" argument to parser and then be able to raise "illegal scope ERROR" when needed
}
