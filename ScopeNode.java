import java.util.ArrayList;

class ScopeNode {

    ArrayList<ScopeNode> sons;

    String data;


    public ScopeNode(String dataLine){
        data = dataLine;
    }

    public void addSon(ScopeNode newSon){
        sons.add(newSon);
    }

    public String getData(){
        return data;
    }

}
