import static org.junit.Assert.*;
import org.junit.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralTests {

    private final static String EMPTY_LINE_REGEX = "\\s*";

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
}
