package oop.ex6.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalScope extends Scope {

    ///////////////////////CONSTANTS////////////////////////

    private static final String VOID = "void";

    private static final String COMMA = ",";
    private static final String NO_RETURN_EXCEPTION = "NoReturnException";

    private HashMap<String, MethodScope> methods;

    public GlobalScope(LineTree tree) {
        super(tree.root, null);
        methods = new HashMap<>();

    }

    private void analyzeGlobalScope() throws ExceptionFileFormat {
        for (LineNode declerLine : root.sons) {
            String line = declerLine.data;
            if (line.startsWith(VOID))
                new MethodScope(declerLine, this);
            else {
                //todo varAssignAnalyzer(line) and varDecAnalyzer(line) which update the var table
            }
        }
    }

    private void verifyAllMethods() throws NoReturnException {
        for (Map.Entry<String, MethodScope> method: methods.entrySet()){
            MethodScope currMethod = method.getValue();
            ArrayList<LineNode> methodBody = currMethod.root.sons;
            if(!methodBody.get(methodBody.size() -1).data.equals(RETURN))
                throw new NoReturnException(NO_RETURN_EXCEPTION);
            currMethod.verifyScope();
        }
    }
}
