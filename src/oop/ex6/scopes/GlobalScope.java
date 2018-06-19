package oop.ex6.scopes;

import oop.ex6.main.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlobalScope extends Scope {

    ///////////////////////CONSTANTS////////////////////////

    private static final String VOID = "void";
    private static final String NO_RETURN_EXCEPTION = "NoReturnException";

    private HashMap<String, MethodScope> methods;

    public GlobalScope(LineTree tree) throws SyntaxException, GlobalScopeException{
        super(tree.getRoot(), null, null);
        try {
            methods = new HashMap<>();
            analyzeGlobalScope();
            verifyAllMethods();
        }catch (NoReturnException | ScopeException | InvalidVariableDeclarationException e){
            throw new GlobalScopeException(e.getMessage(), e);
        }
    }

    private void analyzeGlobalScope() throws SyntaxException, InvalidParameterListException {
        for (LineNode declareLine : root.getSons()) {
            String line = declareLine.getData();
            if (line.startsWith(VOID))
                new MethodScope(declareLine, null, this);
            else {
                //todo varAssignAnalyzer(line) and varDecAnalyzer(line) which update the var table
            }
        }
    }

    private void verifyAllMethods() throws NoReturnException, InvalidVariableDeclarationException, ScopeException {
        for (Map.Entry<String, MethodScope> method: methods.entrySet()){
            MethodScope currMethod = method.getValue();
            ArrayList<LineNode> methodBody = currMethod.root.getSons();
            if(!methodBody.get(methodBody.size() -1).getData().equals(RETURN))
                throw new NoReturnException(NO_RETURN_EXCEPTION);
            currMethod.verifyScope();
        }
    }

    public HashMap<String, MethodScope> getMethods() {
        return methods;
    }
}
