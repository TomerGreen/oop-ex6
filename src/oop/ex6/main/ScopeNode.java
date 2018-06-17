package oop.ex6.main;

import java.util.ArrayList;

class ScopeNode {

    ArrayList<ScopeNode> sons;

    String data;
    ScopeNode parent;
    int lineNumber;


    public ScopeNode(String dataLine, ScopeNode parent, int lineNumber)
    {
        data = dataLine;
        this.parent = parent;
        this.lineNumber = lineNumber;
    }

    public void addSon(ScopeNode newSon){
        sons.add(newSon);
    }

    public String getData(){
        return data;
    }



}
