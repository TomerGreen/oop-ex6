package oop.ex6.scopes;

import oop.ex6.main.*;
import oop.ex6.variables.Variable;
import oop.ex6.variables.VariableParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class GlobalScope extends Scope {

    ///////////////////////CONSTANTS////////////////////////

    private static final String VOID = "void";
    private static final String NO_RETURN_EXCEPTION = "NoReturnException";
    private static final String GLOBAL_RETURN = "return line in global scope";
    private HashMap<String, MethodScope> methods;

    public GlobalScope(LineTree tree) throws SyntaxException, GlobalScopeException{
        super(tree.getRoot(), null, null);
        try {
            methods = new HashMap<>();
            analyzeGlobalScope();
            verifyAllMethods();
        }catch (LogicException e){
            throw new GlobalScopeException(e.getMessage(), e);
        } catch (UnknownVariableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Analyzes the global Scope
     * @throws SyntaxException when syntax problem reached
     * @throws UnknownVariableException when we get to unknown variable
     * @throws LogicException when we get to logical problem in the given code.
     */
    private void analyzeGlobalScope() throws SyntaxException,UnknownVariableException, LogicException{
        if(root.getSons().size() !=0){
            for (LineNode declareLine : root.getSons()) {
                String line = declareLine.getData();
                Matcher returnMatcher = returnPattern.matcher(line);
                if(returnMatcher.matches())
                    throw new SyntaxException.ExceptionFileFormat(GLOBAL_RETURN);
                else if (line.startsWith(VOID))
                    new MethodScope(declareLine, null, this);
                else if (VariableParser.isLegalVarDec(line))
                    parseVarDeclaration(line);
                else if (VariableParser.isLegalAssignment(line))
                    parseAssignment(line);
                else{ throw new SyntaxException.ExceptionFileFormat();}
            }
        }
    }

    /**
     * verify all the methods of the program
     * @throws LogicException when there's logic exception in the given Sjava code
     */
    private void verifyAllMethods() throws LogicException{
        for (Map.Entry<String, MethodScope> method: methods.entrySet()){
            MethodScope currMethod = method.getValue();
            ArrayList<LineNode> methodBody = currMethod.root.getSons();
            int numOfMethodLines = methodBody.size();
            if(numOfMethodLines > 0){
                String lastMethodLine = methodBody.get(numOfMethodLines -1).getData();
                Matcher returnMatcher = returnPattern.matcher(lastMethodLine);
                if(!returnMatcher.matches())
                    throw new NoReturnException(NO_RETURN_EXCEPTION);
                currMethod.verifyScope();
            }
            else
                throw new NoReturnException(NO_RETURN_EXCEPTION);
        }
    }

    /**
     * @param name the name of the new method
     * @param methodScope the new method scope
     */
    void setMethods(String name, MethodScope methodScope){
        methods.put(name, methodScope);
    }

    /**
     * @return the methods of the program
     */
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
