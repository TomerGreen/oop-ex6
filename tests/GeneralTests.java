import static org.junit.Assert.*;

import oop.ex6.main.ExceptionFileFormat;
import oop.ex6.main.LineTree;
import org.junit.*;
<<<<<<< HEAD

import java.io.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
=======
import java.util.regex.*;
import oop.ex6.main.*;
>>>>>>> e4657c340a3fa7a1d5de9ead34743be3ab6642d8

public class GeneralTests {

    private final static String EMPTY_LINE_REGEX = "\\s*";
    private static final String FINAL_VARIABLE_DECLARATION_PREFIX = "(?:(final) )?";
    private static final String VARIABLE_TYPES_REGEX = "((?:int)|(?:boolean)|(?:String)|(?:double)|(?:char)) ";
    private static final String LEGAL_VAR_NAME_REGEX = "(_*[a-zA-z]\\w*)";
    private static final String VARIABLE_SIGNATURE_REGEX = FINAL_VARIABLE_DECLARATION_PREFIX +
            VARIABLE_TYPES_REGEX + LEGAL_VAR_NAME_REGEX;

    @Test
    public void testSplit() {
        String line = "                    a b  c";
//        String[] tokens = line.split("\\s");
        List<String> tokens = Arrays.asList(line.split("\\s"));
//        tokens.remove("");
        for (String token : tokens) {
                System.out.println("'" + token + "'");
        }
//        assertTrue(tokens.length == 3);
        assertEquals(3, tokens.size());
    }

    @Test
    public void testEmptyLineREGEX() {
        String line = " ";
        Pattern emptyLinePattern = Pattern.compile("^\\s*$");
        Matcher emptyLineMatcher = emptyLinePattern.matcher(line);
//        assertTrue(line.startsWith());
        assertTrue(emptyLineMatcher.find());
    }

    @Test
    public void testVariableDecRegex() {
        String finalVar = "final int __aaaa___aaaa";
        String nonFinalVar = "boolean aaa999";
        Pattern varSigPattern = Pattern.compile(VARIABLE_SIGNATURE_REGEX);
        Matcher final_var_matcher = varSigPattern.matcher(finalVar);
        Matcher non_final_var_matcher = varSigPattern.matcher(nonFinalVar);
        assertTrue(final_var_matcher.matches());
        assertTrue(non_final_var_matcher.matches());
        assertTrue(final_var_matcher.group(1).equals("final"));
    }

    @Test
    public void testVarDecRegex() {
        String basic_dec = "boolean a;";
        String basic_assignment = "boolean a=5;";
        String basic_final_dec = "final int a=5;";
        String basic_dec_with_spaces = "final double a = 5 ;";
        String weird_name_dec = "char ___aaaa1234____111 = 1;";
        String weird_value_dec = "String a = \"  ___  aaa 1111___ \";";
        String multiple_vars = "final int a,b,c;";
        String multiple_vars_with_spaces = "final int a , b , c ;";
        String multiple_vars_with_assignments = "double a, b=5, c=0.5;";
        String hard_multiple_vars = "final double __11__a, b1 = \"aaa\", c=0.55555aaaa, d,e , f = 123asdv.,; ;";
        String missing_spaces1 = "finalint a=5;";
        String missing_semicolon = "final int a";
        String bad_var_name = "int 1a;";
        String missing_spaces2 = "inta;";
        assertTrue(VariableParser.isVarDec(basic_dec));
        assertTrue(VariableParser.isVarDec(basic_assignment));
        assertTrue(VariableParser.isVarDec(basic_final_dec));
        assertTrue(VariableParser.isVarDec(basic_dec_with_spaces));
        assertTrue(VariableParser.isVarDec(weird_name_dec));
        assertTrue(VariableParser.isVarDec(weird_value_dec));
        assertTrue(VariableParser.isVarDec(multiple_vars));
        assertTrue(VariableParser.isVarDec(multiple_vars_with_spaces));
        assertTrue(VariableParser.isVarDec(multiple_vars_with_assignments));
        assertTrue(VariableParser.isVarDec(hard_multiple_vars));
        assertFalse(VariableParser.isVarDec(missing_spaces1));
        assertFalse(VariableParser.isVarDec(missing_semicolon));
        assertFalse(VariableParser.isVarDec(bad_var_name));
        assertFalse(VariableParser.isVarDec(missing_spaces2));

    }

    @Test
    public void testLineTree() throws IOException, ExceptionFileFormat {
        File sjava = new File("C:\\Users\\guygr\\Desktop\\oop\\tester_ex6_1.1\\tester_files\\Tests\\SchoolTests\\test001.java");
        BufferedReader br = new BufferedReader(new FileReader(sjava));
        //here we parse the text sjava file to tree of nested scopes
        LineTree parsedFile = new LineTree(br);
    }
}
