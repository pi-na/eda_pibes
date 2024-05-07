import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class EvaluatorInfijaTest {

    @Test
    public void testSimpleAddition() {
        String input = "3 + 4";
        assertEquals(Double.valueOf(7), injectInputAndGetResult(input));
    }

    @Test
    public void testSimpleSubtraction() {
        String input = "10 - 2";
        assertEquals(Double.valueOf(8), injectInputAndGetResult(input));
    }

    @Test
    public void testMultiplicationAndAddition() {
        String input = "5 * 2 + 3";
        assertEquals(Double.valueOf(13), injectInputAndGetResult(input));
    }

    @Test
    public void testDivisionAndSubtraction() {
        String input = "18 / 3 - 2";
        assertEquals(Double.valueOf(4), injectInputAndGetResult(input));
    }

    @Test
    public void testComplexExpression() {
        String input = "4 + 18 / ( 9 - 3 )";
        assertEquals(Double.valueOf(7), injectInputAndGetResult(input));
    }

    @Test
    public void testExponentiation() {
        String input = "2 ^ 3";
        assertEquals(Double.valueOf(8), injectInputAndGetResult(input));
    }

    @Test
    public void testParentheses() {
        String input = "( 3 + 2 ) * ( 2 + 3 )";
        assertEquals(Double.valueOf(25), injectInputAndGetResult(input));
    }

    @Test
    public void testNegativeNumbers() {
        String input = "-3 + 5";
        assertEquals(Double.valueOf(2), injectInputAndGetResult(input));
    }

    @Test
    public void testFloatingPoint() {
        String input = "3.5 * 2";
        assertEquals(Double.valueOf(7), injectInputAndGetResult(input));
    }

    @Test
    public void testInvalidExpression() {
        String input = "3 +";
        try {
            injectInputAndGetResult(input);
            fail("Expected a RuntimeException due to invalid expression");
        } catch (RuntimeException e) {
            // Expected exception
        }
    }

    private Double injectInputAndGetResult(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        EvaluatorInfija evaluator = new EvaluatorInfija();
        return evaluator.evaluate();
    }
}
