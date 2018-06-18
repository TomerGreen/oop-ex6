package oop.ex6.main;

import java.util.ArrayList;

public class LineNode {

    private ArrayList<LineNode> sons;
    private String data;
    private LineNode parent;
    private int lineNumber;


    public LineNode(String dataLine, LineNode parent, int lineNumber)
    {
        data = dataLine;
        this.parent = parent;
        this.lineNumber = lineNumber;
        sons = new ArrayList<>();
    }

    public void addSon(LineNode newSon){
        sons.add(newSon);
    }

    public String getData(){
        return data;
    }

    public ArrayList<LineNode> getSons() {
        return sons;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public LineNode getParent() {
        return parent;
    }
}
