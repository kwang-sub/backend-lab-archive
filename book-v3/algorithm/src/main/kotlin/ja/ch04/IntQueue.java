package ja.ch04;

public class IntQueue {
    private int max;
    private int num;
    private int front;
    private int rear;
    private int[] que;

    public class OverflowQueException extends RuntimeException {
        public OverflowQueException() {
        }
    }

    public class EmptyQueException extends RuntimeException {
        public EmptyQueException() {
        }
    }

    public IntQueue(int capacity) {
        max = capacity;
        front = 0;
        rear = 0;
        num = 0;
        que = new int[max];
    }

    public int enqueue(int x) {
        if (num >= max) {
            throw new OverflowQueException();
        }
        que[rear++] = x;
        num++;
        if (rear >= max) {
            rear = 0;
        }
        return x;
    }

    public int dequeue() {
        if (num <= 0) {
            throw new EmptyQueException();
        }
        int result = que[front++];
        num--;
        if (front >= max) {
            front = 0;
        }

        return result;
    }

    public int peek() {
        if (num <= 0) {
            throw new EmptyQueException();
        }
        return que[front];
    }

    public int indexOf(int x) {
        for (int i = 0; i < num; i++) {
            int idx = i + front % max;
            if (que[idx] == x) {
                return idx;
            }
        }
        return -1;
    }

    public void clear() {
        num = front = rear = 0;
    }

    public int capacity() {
        return max;
    }

    public int size() {
        return num;
    }

    public boolean isEmpty() {
        return num <= 0;
    }

    public boolean isFull() {
        return num >= max;
    }

    public void dump() {
        for (int i = 0; i < num; i++) {
            System.out.print(que[(i + front) % max] + " ");
        }
        System.out.println();
    }

    public int search(int x) {
        for (int i = 0; i < num; i++) {
            int idx = (i + front) % max;
            if (que[idx] == x) {
                return i + 1;
            }
        }
        return 0;
    }
}
