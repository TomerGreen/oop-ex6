import java.io.BufferedReader;
import java.io.IOException;

public class LineTree {

    private final static String COMMENT_PREFIX = "//";
    private final static String ROOT_LINE = "class;";
    private final static String SINGLE_LINE_SUFFIX = ";";
    private final static String SPACES = "\\s";
    private final static int EMPTY = 0;
    private final static String METHOD_DEC = "void";
    private final static String END_OF_SCOPE = "}";

    ScopeNode root;

    LineTree(BufferedReader br) throws IOException, ExceptionFileFormat {
        root = parser(br, new ScopeNode(ROOT_LINE, null));
    }

    /**
     * method which parse the sjava file to a tree of nested scopes which is easier to analyze.
     * @param br a BufferedReader of the original file we parse
     */
    private ScopeNode parser(BufferedReader br, ScopeNode currRoot) throws IOException, ExceptionFileFormat {
        String line;
        line = br.readLine();
        while (line != null){
            String[] tokens = line.split(SPACES);
            if(!commentOrEmptyCase(line, tokens)){
                if(singleLineCase(tokens)) {
                    currRoot.addSon(new ScopeNode(line, currRoot));
                }
                else if(tokens[0].equals(METHOD_DEC)){
                    currRoot.addSon(parser(br, new ScopeNode(line, currRoot)));
                }
                else if(endOfScopeCase(tokens) && currRoot.parrent != null){
                    currRoot = currRoot.parrent;
                }
                else{
                    throw new ExceptionFileFormat();
                }
            }
            line = br.readLine();
        }
        if(currRoot.getData().equals(ROOT_LINE)){
            return currRoot;
        }
        else {
            throw new ExceptionFileFormat();
        }
    }

    private boolean commentOrEmptyCase(String line, String[] tokens){
        return ( line.startsWith(COMMENT_PREFIX) || (tokens.length == EMPTY) );
    }

    private boolean singleLineCase(String[] tokens){
        String lastToken = tokens[tokens.length - 1];
        return lastToken.endsWith(SINGLE_LINE_SUFFIX);
    }
    private boolean endOfScopeCase(String[] tokens){
        return (tokens.length == 1) && tokens[0].equals(END_OF_SCOPE);
    }
}
