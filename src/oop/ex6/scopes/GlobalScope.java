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

    public GlobalScope(LineTree tree) throws ExceptionFileFormat, InvalidVariableDeclarationException,
            UnfamiliarMethodName, NoReturnException, IllegalMethodCallException,
            UninitializedVariableUsageException, InvalidAssignmentException, UnrecognizedVariableTypeException {
        super(tree.getRoot(), null, null);
        methods = new HashMap<>();
        analyzeGlobalScope();
        verifyAllMethods();
    }

    private void analyzeGlobalScope() throws ExceptionFileFormat {
        for (LineNode declareLine : root.getSons()) {
            String line = declareLine.getData();
            if (line.startsWith(VOID))
                new MethodScope(declareLine, null, this);
            else {
                //todo varAssignAnalyzer(line) and varDecAnalyzer(line) which update the var table
            }
        }
    }

    private void verifyAllMethods() throws NoReturnException, InvalidVariableDeclarationException,
            UnfamiliarMethodName, InvalidAssignmentException, IllegalMethodCallException,
            UninitializedVariableUsageException, UnrecognizedVariableTypeException {
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
