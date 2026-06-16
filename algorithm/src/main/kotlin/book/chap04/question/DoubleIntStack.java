package book.chap04.question;

public class DoubleIntStack {
    private int max;
    private int lPtr;
    private int rPtr;
    private int[] stk;

    public DoubleIntStack(int max) {
        this.max = max;
        lPtr = 0;
        rPtr = max - 1;
        stk = new int[max];
    }

    public int push(int i, Direction direction) {
        if (direction.equals(Direction.LEFT)) {
            return leftPush(i);
        } else {
            return rightPush(i);
        }

    }

    private int rightPush(int i) {
        if (rPtr < lPtr || rPtr < 0) throw new OverflowIntStackException();
        return stk[rPtr--] = i;
    }

    private int leftPush(int i) {
        if (lPtr > rPtr || lPtr >= max) throw new OverflowIntStackException();
        return stk[lPtr++] = i;
    }

    public int pop(Direction direction) {
        if (direction.equals(Direction.LEFT)) {
            return leftPop();
        } else {
            return rightPop();
        }
    }

    private int rightPop() {
        if (rPtr >= max - 1) throw new EmptyIntStackException();
        return stk[++rPtr];
    }

    private int leftPop() {
        if (lPtr <= 0) throw new EmptyIntStackException();
        return stk[--lPtr];
    }

    public enum Direction {
        LEFT, RIGHT
    }

    public class EmptyIntStackException extends RuntimeException {
        public EmptyIntStackException() {
        }
    }

    public class OverflowIntStackException extends RuntimeException {
        public OverflowIntStackException() {
        }
    }
}
