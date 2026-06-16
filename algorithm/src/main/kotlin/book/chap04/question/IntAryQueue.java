package book.chap04.question;

public class IntAryQueue {
    private int max;
    private int num;
    private int[] que;


    public IntAryQueue(int max) {
        this.max = max;
        num = 0;
        que = new int[max];
    }

    public int enqueue(int i) {
        if (num >= max) throw new OverflowException();
        return que[num++] = i;
    }

    public int dequeue() {
        if (num <= 0) throw new EmptyException();
        int result = que[0];
        if (num > 1) {
            for (int i = 1; i < num; i++) {
                que[i - 1] = que[i];
            }
        }
        num -= 1;

        return result;
    }

    static class OverflowException extends RuntimeException {
    }

    static class EmptyException extends RuntimeException {
    }
}
