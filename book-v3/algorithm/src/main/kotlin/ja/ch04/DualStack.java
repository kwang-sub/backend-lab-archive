package ja.ch04;

public class DualStack {
    private int lPtr;
    private int rPtr;
    private int[] array;

    public DualStack(int size) {
        this.lPtr = 0;
        this.rPtr = size - 1;
        array = new int[size];
    }

    public class EmptyIntStackException extends RuntimeException {
        public EmptyIntStackException() {
        }
    }

    public class OverflowIntStackException extends RuntimeException {
        public OverflowIntStackException() {
        }
    }

    public int push(Point pt, int n) {
        if (lPtr >= rPtr + 1) throw new OverflowIntStackException();
        if (pt == Point.LEFT) {
            array[lPtr++] = n;
        } else {
            array[rPtr--] = n;
        }
        return n;
    }

    public int pop(Point pt) {
        if (pt == Point.LEFT) {
            if (lPtr <= 0) throw new EmptyIntStackException();
            return array[--lPtr];
        } else {
            if (rPtr >= array.length - 1) throw new EmptyIntStackException();
            return array[++rPtr];
        }
    }

    public enum Point {
        LEFT, RIGHT
    }
}
