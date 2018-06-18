package oop.ex6.scopes;

import oop.ex6.main.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlobalScope extends Scope {

    ///////////////////////CONSTANTS////////////////////////

    private static final String VOID = "void";

    private static final String COMMA = ",";
    private static final String NO_RETURN_EXCEPTION = "NoReturnException";

    private HashMap<String, MethodScope> methods;

    public GlobalScope(LineTree tree) {
        super(tree.getRoot(), null);
        methods = new HashMap<>();

    }

    private void analyzeGlobalScope() throws ExceptionFileFormat {
        for (LineNode declerLine : root.getSons()) {
            String line = declerLine.getData();
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
            ArrayList<LineNode> methodBody = currMethod.root.getSons();
            if(!methodBody.get(methodBody.size() -1).getData().equals(RETURN))
                throw new NoReturnException(NO_RETURN_EXCEPTION);
            currMethod.verifyScope();
        }
    }
}
