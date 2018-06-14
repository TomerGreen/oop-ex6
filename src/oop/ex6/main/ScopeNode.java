import java.util.ArrayList;

class ScopeNode {

    ArrayList<ScopeNode> sons;

    String data;
    ScopeNode parrent;


    public ScopeNode(String dataLine, ScopeNode parrent)
    {
        data = dataLine;
        this.parrent = parrent;
    }

    public void addSon(ScopeNode newSon){
        sons.add(newSon);
    }

    public String getData(){
        return data;
    }

    //todo think about parent attribute

}
