import static org.junit.Assert.*;
import org.junit.*;

public class GeneralTests {

    @Test
    public void testSplit() {
        String line = "a b  c";
        String[] tokens = line.split("\\s");
        for (String token : tokens) {
            System.out.println("'" + token + "'");
        }
        assertTrue(tokens.length == 3);
    }
}
