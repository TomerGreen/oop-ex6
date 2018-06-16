import static org.junit.Assert.*;
import org.junit.*;
import java.util.regex.*;

public class GeneralTests {

    private static final String FINAL_VARIABLE_DECLARATION_PREFIX = "(?:(final) )?";
    private static final String VARIABLE_TYPES_REGEX = "((?:int)|(?:boolean)|(?:String)|(?:double)|(?:char)) ";
    private static final String LEGAL_VAR_NAME_REGEX = "(_*[a-zA-z]\\w*)";
    private static final String VARIABLE_SIGNATURE_REGEX = FINAL_VARIABLE_DECLARATION_PREFIX +
            VARIABLE_TYPES_REGEX + LEGAL_VAR_NAME_REGEX;

    @Test
    public void testSplit() {
        String line = "a b  c";
        String[] tokens = line.split("\\s");
        for (String token : tokens) {
            System.out.println("'" + token + "'");
        }
        assertTrue(tokens.length == 3);
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
}
