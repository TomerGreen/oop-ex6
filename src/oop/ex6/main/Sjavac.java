package oop.ex6.main;

import oop.ex6.scopes.GlobalScope;
import oop.ex6.scopes.GlobalScopeException;

import java.io.*;

public class Sjavac {



    //////////////////////CONSTANTS/////////////////////////////////////////
    private final static int NUM_OF_PARAMETERS = 1;
    private final static String FILE_FORMAT_IS_OK = "0";
    private final static String BAD_FORMAT_ERROR = "1";
    private final static String PARAMETERS_ERROR = "2";
    private final static String SJAVA_TYPE = ".sjava";

    public static void main(String args[]){
        try {
            if (!(args.length == NUM_OF_PARAMETERS && args[0].endsWith(SJAVA_TYPE)))
                throw new IOException(PARAMETERS_ERROR);
            File SjavaFile = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(SjavaFile));
            //here we parse the text sjava file to tree of nested scopes
            LineTree parsedFile = new LineTree(br);
            //here we analyze all the relevant data in the main class scope (A.K.A global)
            GlobalScope gllobalTable = new GlobalScope(parsedFile);


            System.out.println(FILE_FORMAT_IS_OK);

        }catch (IOException e) {
            System.out.println(PARAMETERS_ERROR);
        } catch (SyntaxException|GlobalScopeException e) {
            System.out.println(BAD_FORMAT_ERROR);
            System.err.println(e.getMessage());
        }

    }


}