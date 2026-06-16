package ja.ch04;

// TODO 재구현 필요
public class IntDeque {

    private int max;
    private int num;
    private int front;
    private int rear;
    private int[] que;

    public IntDeque(int size) {
        this.max = size;
        this.que = new int[size];
        this.front = 0;
        this.rear = 0;
        this.num = 0;
    }

    public int enqueueFr(int x) {
        if (num >= max) {
            return -1;
        }
        if (--front < 0) {
            front = max - 1;
        }
        num++;
        que[front] = x;
        return x;
    }

    public int enqueueRe(int x) {
        if (num >= max) {
            return -1;
        }
        que[rear++] = x;
        if (rear >= max) {
            rear = 0;
        }
        num++;
        return x;
    }

    public int dequeueFr() {
        if (num <= 0) {
            return -1;
        }
        int result = que[front];
        if (++front >= max) {
            front = 0;
        }
        num--;
        return result;
    }

    public int dequeueRe() {
        if (num <= 0) {
            return -1;
        }
        if (--rear < 0) {
            rear = max - 1;
        }
        num--;
        return que[rear];
    }
}
