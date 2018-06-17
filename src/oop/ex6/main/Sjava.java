package oop.ex6.main;

import java.io.*;

public class Sjava {



    //////////////////////CONSTANTS/////////////////////////////////////////
    private final static int NUM_OF_PARAMETERS = 1;
    private final static String BAD_FORMAT_ERROR = "1";
    private final static String PARAMETERS_ERROR = "2";
    private final static String SJAVA_TYPE = "Sjava";
    private final static String ARGUMENTS_ERROR = "arguments error";
    private final static String FILE_FORMAT_ILLEGAL = "file format illegal";


    public void main(String args[]){
        try{
            if(!(args.length == NUM_OF_PARAMETERS && args[0].endsWith(SJAVA_TYPE)))
                throw new ExceptionArgs(PARAMETERS_ERROR);
            File SjavaFile = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(SjavaFile));
            //here we parse the text sjava file to tree of nested scopes
            LineTree parsedFile = new LineTree(br);
            //here we analyze all the relevant data in the main1 class scope (A.K.A global)

            GlobalScope gllobalTable = new GlobalScope(parsedFile);

            // todo send the root to global class statement constructor and receive Symbol tables

            //todo run over the LineTree and verify all the methods

        }catch (IOException e) {
            System.err.println(ARGUMENTS_ERROR);
            System.err.println(PARAMETERS_ERROR);
        } catch (ExceptionFileFormat exceptionFileFormat) {
            System.err.println(FILE_FORMAT_ILLEGAL);
            System.err.println(BAD_FORMAT_ERROR);
        }


    }


}