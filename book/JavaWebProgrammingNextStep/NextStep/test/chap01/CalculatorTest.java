package chap01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CalculatorTest {
    private Calculator cal;

    @Before
    public void setup() {
        cal = new Calculator();
        System.out.println("before");
    }

    @Test
    public void add() {
        System.out.println("add");
        assertEquals(9, cal.add(6, 3));
    }

    @Test
    public void subtract() {
        System.out.println("subtract");
        assertEquals(3, cal.subtract(6, 3));
    }

    @Test
    public void multiply() {
        System.out.println("multiply");
        assertEquals(10, cal.multiply(2, 5));
    }

    @Test
    public void divide() {
        System.out.println("divide");
        assertEquals(2, cal.divide(6, 3));
    }

    @After
    public void teardown() {
        System.out.println("teardown");
    }
}