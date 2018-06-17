package oop.ex6.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalScope extends Scope {

    ///////////////////////CONSTANTS////////////////////////

    //todo "more elegant" regex (now that line whitespaces are organized

    private static final int VAR_DEC_TYPE = 0;
    private static final int VAR_ASSIGHNMENT_TYPE = 1;
    private static final int METHOD_TYPE = 2;

    private static final String COMMA = ",";

    private HashMap<String, MethodScope> methods;

    public GlobalScope(LineTree tree) {
        super(tree.root, null);
        methods = new HashMap<>();

    }

    private void analyzeGlobalScope() throws ExceptionFileFormat {
        for (LineNode declerLine: root.sons) {
            String line = declerLine.data;
            int scopeType = getDeclareType(line);
            switch (scopeType){
                case (VAR_DEC_TYPE):
                    varDecAnalyzer(line);
                    break;
                case(VAR_ASSIGHNMENT_TYPE):
                    varAssignAnalyzer(line);
                    break;
                case(METHOD_TYPE):
                    methodDecAnalyzer(line);
                    break;
            }
        }
    }

    private void varDecAnalyzer(String deceleration){
        //todo take from scope
    }

    private void varAssignAnalyzer(String deceleration){
        //todo take from string
    }


    //todo move to MethodScope




    // todo send to method of variable class or finish writing this method
    private ArrayList<Variable> getArgsList(String argsDeclare){
        ArrayList<Variable> argList = new ArrayList<>();
        String[] args = argsDeclare.split(COMMA);
        for(String arg : args){
            //argList.add(VaribleFactory.analyzeVariable(arg)); // todo change to explicit method call
        }
        return argList;
    }

    private int getDeclareType(String line){
        //todo
        return -1;
    }
}
