package book.chap04.question;

public class Deck {
    private int max;
    private int num;
    private int front;
    private int rear;


    private int[] que;

    public Deck(int max) {
        this.max = max;
        front = rear = num = 0;
        que = new int[max];
    }

    public class EmptyIntDeckException extends RuntimeException {
        public EmptyIntDeckException() {
        }
    }

    public class OverflowDeckQueueException extends RuntimeException {
        public OverflowDeckQueueException() {
        }
    }

    public int enqueFront(int x) {
        if (num >= max) throw new OverflowDeckQueueException();
        num++;
        if (--front < 0) front = max - 1;
        que[front] = x;
        return x;
    }

    public int enqueRear(int x) {
        if (num >= max) throw new OverflowDeckQueueException();
        num++;
        que[rear++] = x;
        if (rear >= max) rear = 0;
        return x;
    }

    public int dequeFront() {
        if (num <= 0) throw new EmptyIntDeckException();
        num--;
        int result = que[front++];
        if (front <= max) front = 0;
        return result;
    }

    public int dequeRear() {
        if (num <= 0) throw new EmptyIntDeckException();
        num--;
        if (--rear < 0) rear = max - 1;
        return que[rear];
    }

    public int[] getQue() {
        return que;
    }

    public int getNum() {
        return num;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }
}
