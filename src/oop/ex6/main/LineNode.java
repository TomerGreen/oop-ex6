package oop.ex6.main;

import java.util.ArrayList;

/**
 * A class of the Line Node object
 */
public class LineNode {

    private ArrayList<LineNode> sons;
    private String data;
    private LineNode parent;
    private int lineNumber;


    /**
     *
     * @param dataLine the original specific line of the code
     * @param parent the parent's node
     * @param lineNumber the lineNumber in the original Sjava code
     */
    LineNode(String dataLine, LineNode parent, int lineNumber) {
        data = dataLine;
        this.parent = parent;
        this.lineNumber = lineNumber;
        sons = new ArrayList<>();
    }

    /**
     * adds a son to the node
     * @param newSon a new son of the node
     */
    void addSon(LineNode newSon){
        sons.add(newSon);
    }

    /**
     * @return the data (the original line) of the node
     */
    public String getData(){
        return data;
    }

    /**
     * @return the sons of the node
     */
    public ArrayList<LineNode> getSons() {
        return sons;
    }

    /**
     * @return the line number
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * @return the parent on the node
     */
    LineNode getParent() {
        return parent;
    }
}
