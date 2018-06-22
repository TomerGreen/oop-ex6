package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GlobalScope extends Scope {

    ///////////////////////CONSTANTS////////////////////////

    private static final String VOID = "void";
    private static final String NO_RETURN_EXCEPTION = "NoReturnException";
    private static final String GLOBAL_RETURN = "return line in global scope";
    HashMap<String, MethodScope> methods;

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
        if(root.getSons().size() !=0){
            for (LineNode declareLine : root.getSons()) {
                String line = declareLine.getData();
                if(line.matches(RETURN))
                    throw new ExceptionFileFormat(GLOBAL_RETURN);
                else if (line.startsWith(VOID))
                    new MethodScope(declareLine, null, this);
                else if (VariableParser.isLegalVarDec(line))
                    parseVarDeclaration(line);
                else if (VariableParser.isLegalAssignment(line))
                    parseAssignment(line);
                else{ throw new  ExceptionFileFormat();}
            }
        }
    }

    private void verifyAllMethods() throws NoReturnException, InvalidVariableDeclarationException, ScopeException {
        for (Map.Entry<String, MethodScope> method: methods.entrySet()){
            MethodScope currMethod = method.getValue();
            ArrayList<LineNode> methodBody = currMethod.root.getSons();
            int numOfMethodLines = methodBody.size();
            if(numOfMethodLines > 0){
                String lastMethodLine = methodBody.get(numOfMethodLines -1).getData();
                if(!lastMethodLine.matches(RETURN))
                    throw new NoReturnException(NO_RETURN_EXCEPTION);
                currMethod.verifyScope();
            }
            else
                throw new NoReturnException(NO_RETURN_EXCEPTION);
        }
    }

    public void setMethods(String name, MethodScope methodScope){
        methods.put(name, methodScope);
    }

    HashMap<String, MethodScope> getMethods() {
        return methods;
    }

    @Override
    protected Variable getDefinedVariable(String name) throws UnknownVariableException {
        if (this.variables.containsKey(name)) {
            return variables.get(name);
        }
        else
            throw new UnknownVariableException("Variable name \"" + name + "\" is undefined.");
    }
}
