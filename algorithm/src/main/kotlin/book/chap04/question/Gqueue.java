package book.chap04.question;

public class Gqueue<E> {
    private int max;
    private int num;
    private int front;
    private int rear;
    private E[] que;

    public Gqueue(int max) {
        this.max = max;
        num = front = rear = 0;
        que = (E[]) new Object[max];
    }

    public E enque(E e) {
        if (num >= max) throw new OverflowGQueueException();
        que[rear++] = e;
        num++;
        if (rear >= max) rear = 0;

        return e;
    }

    public E deque() {
        if (num <= 0) throw new EmptyGQueueException();
        E reuslt = que[front++];
        num--;
        if (front >= max) front = 0;

        return reuslt;
    }


}

class EmptyGQueueException extends RuntimeException {
}

class OverflowGQueueException extends RuntimeException {
}
