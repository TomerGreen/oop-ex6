package oop.ex6.main;

import java.util.ArrayList;

class LineNode {

    ArrayList<LineNode> sons;

    String data;
    LineNode parent;
    int lineNumber;


    public LineNode(String dataLine, LineNode parent, int lineNumber)
    {
        data = dataLine;
        this.parent = parent;
        this.lineNumber = lineNumber;
    }

    public void addSon(LineNode newSon){
        sons.add(newSon);
    }

    public String getData(){
        return data;
    }



}
