package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.VariableParser;

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
        }catch (NoReturnException | ScopeException | InvalidVariableDeclarationException | InvalidAssignmentException
                | UninitializedVariableUsageException e){
            throw new GlobalScopeException(e.getMessage(), e);
        } catch (UnknownVariableException e) {
            e.printStackTrace();
        }
    }

    private void analyzeGlobalScope() throws SyntaxException, InvalidVariableDeclarationException,
            UnknownVariableException, InvalidAssignmentException, UninitializedVariableUsageException {
        for (LineNode declareLine : root.getSons()) {
            String line = declareLine.getData();
            if (line.startsWith(VOID))
                new MethodScope(declareLine, null, this);
            else if (VariableParser.isLegalVarDec(line))
                parseVarDeclaration(line);
            else if (VariableParser.isLegalAssignment(line))
                parseAssignment(line);
            else{ throw new  ExceptionFileFormat();}
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

    HashMap<String, MethodScope> getMethods() {
        return methods;
    }
}
