package chap01;

public class OldCalculatorTest {
    public static void main(String[] args) {
        Calculator cal = new Calculator();

        add(cal);
        subtract(cal);
        divide(cal);
        multiply(cal);
    }

    private static void divide(Calculator cal) {
        System.out.println(cal.divide(8, 4));
    }

    private static void add(Calculator cal) {
        System.out.println(cal.add(3, 4));
    }

    private static void subtract(Calculator cal) {
        System.out.println(cal.subtract(5, 4));
    }

    private static void multiply(Calculator cal) {
        System.out.println(cal.multiply(2, 4));
    }

}
