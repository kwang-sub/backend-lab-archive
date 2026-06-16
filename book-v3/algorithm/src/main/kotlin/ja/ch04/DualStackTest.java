package ja.ch04;

public class DualStackTest {
    public static void main(String[] args) {
        DualStack d = new DualStack(5);

        d.push(DualStack.Point.RIGHT, 10);
        d.push(DualStack.Point.RIGHT, 11);
        d.push(DualStack.Point.LEFT, 12);
        d.push(DualStack.Point.LEFT, 13);
        d.push(DualStack.Point.LEFT, 14);
        System.out.println(d.pop(DualStack.Point.RIGHT));
        System.out.println(d.pop(DualStack.Point.RIGHT));
    }
}
