import java.io.File;

public class Sjava {


    public void main(String args[]){
        File SjavaFile = new File(args[0]);  //todo check if input valid and return 2
        //here we parse the text sjava file to tree of nested scopes
        LineTree parsedFile = new LineTree(SjavaFile);
        //here we analyze all the relevant data in the main class scope (A.K.A global)
        SymbolTable gllobalTable = SyntaxAnalyzer(parsedFile);


    }

    // todo send the root to global class statement constructor and receive Symbol tables

    //todo run over the LineTree and verify all the methods
}