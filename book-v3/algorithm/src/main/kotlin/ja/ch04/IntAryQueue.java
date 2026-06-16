package ja.ch04;

public class IntAryQueue {
    private int max;
    private int num;
    private int[] que;

    public class OverflowQueException extends RuntimeException {

        public OverflowQueException() {
        }
    }

    public class EmptyQueException extends RuntimeException {
        public EmptyQueException() {
        }
    }

    public IntAryQueue(int capacity) {
        max = capacity;
        num = 0;
        que = new int[max];
    }

    public int enqueue(int x) {
        if (num >= max) throw new OverflowQueException();
        que[num++] = x;
        return x;
    }

    public int dequeue() {
        if (num <= 0) throw new EmptyQueException();
        int result = que[0];

        for (int i = 1; i < num; i++) {
            que[i - 1] = que[i];
        }
        num -= 1;
        return result;
    }
}
