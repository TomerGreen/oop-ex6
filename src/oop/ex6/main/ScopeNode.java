package oop.ex6.main;

import java.util.ArrayList;

class ScopeNode {

    ArrayList<ScopeNode> sons;

    String data;
    ScopeNode parent;


    public ScopeNode(String dataLine, ScopeNode parent)
    {
        data = dataLine;
        this.parent = parent;
    }

    public void addSon(ScopeNode newSon){
        sons.add(newSon);
    }

    public String getData(){
        return data;
    }

    //todo think about parent attribute

}
