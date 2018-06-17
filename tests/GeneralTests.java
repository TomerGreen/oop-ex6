import static org.junit.Assert.*;

import oop.ex6.main.ExceptionFileFormat;
import oop.ex6.main.LineTree;
import org.junit.*;

import java.io.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println(non_final_var_matcher.group(1));
        System.out.println(non_final_var_matcher.group(2));
        System.out.println(non_final_var_matcher.group(3));
        System.out.println(non_final_var_matcher.group(4));
    }

    @Test
    public void testLineTree() throws IOException, ExceptionFileFormat {
        File sjava = new File("C:\\Users\\guygr\\Desktop\\oop\\tester_ex6_1.1\\tester_files\\Tests\\SchoolTests\\test001.java");
        BufferedReader br = new BufferedReader(new FileReader(sjava));
        //here we parse the text sjava file to tree of nested scopes
        LineTree parsedFile = new LineTree(br);
    }
}
