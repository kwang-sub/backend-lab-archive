package book.chap04.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntAryQueueTest {

    private IntAryQueue intAryQueue;

    @BeforeEach
    void init() {
        intAryQueue = new IntAryQueue(3);
    }

    @Test
    void enqueueTest() {
        int enqueue = intAryQueue.enqueue(10);
        assertThat(enqueue).isEqualTo(10);

    }

    @Test
    void enqueueExceptionTest() {
        int enqueue1 = intAryQueue.enqueue(1);
        int enqueue2 = intAryQueue.enqueue(2);
        int enqueue3 = intAryQueue.enqueue(10);
        assertThat(enqueue3).isEqualTo(10);
        assertThrows(IntAryQueue.OverflowException.class, () -> intAryQueue.enqueue(10));
    }

    @Test
    void dequeueTest() {
        int enqueue1 = intAryQueue.enqueue(1);
        int enqueue2 = intAryQueue.enqueue(2);

        int dequeue1 = intAryQueue.dequeue();
        int dequeue2 = intAryQueue.dequeue();

        assertThat(dequeue1).isEqualTo(1);
        assertThat(dequeue2).isEqualTo(2);
        assertThrows(IntAryQueue.EmptyException.class, () -> intAryQueue.dequeue());
    }

}