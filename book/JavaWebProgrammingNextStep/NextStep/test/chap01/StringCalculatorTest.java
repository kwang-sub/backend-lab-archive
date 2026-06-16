package chap01;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    StringCalculator cal;

    @Before
    public void setup() {
        cal  = new StringCalculator();
    }

    @Test
    public void nullTest() {

        assertEquals(0, cal.add(null));
    }

    @Test
    public void integerTest() {
        assertEquals(1, cal.add("1"));
    }

    @Test
    public void stringSum() {
        assertEquals(3, cal.add("1,2"));
    }

    @Test
    public void stringSumTriple() {
        assertEquals(6, cal.add("1,2|3"));
    }

    @Test
    public void customStringSum() {
        assertEquals(6, cal.add("//;\n1;2;3"));
    }

    @Test(expected = RuntimeException.class)
    public void throwException(){
        cal.add("-1");
    }

}