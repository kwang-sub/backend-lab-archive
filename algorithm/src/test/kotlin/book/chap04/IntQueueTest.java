package book.chap04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntQueueTest {

    private IntQueue intQueue;

    @BeforeEach
    void init() {
        intQueue = new IntQueue(3);
    }

    @Test
    void enqueTest() {
        int enque1 = intQueue.enque(1);
        int enque2 = intQueue.enque(2);
        int deque1 = intQueue.deque();
        int enque3 = intQueue.enque(3);
        int enque4 = intQueue.enque(4);

        assertThat(enque2).isEqualTo(2);
        assertThat(deque1).isEqualTo(1);
    }

    @Test
    void searchTest() {
        intQueue.enque(1);
        intQueue.enque(2);
        intQueue.deque();
        intQueue.enque(3);
        intQueue.enque(4);

        int search1 = intQueue.search(1);
        int search3 = intQueue.search(3);


        assertThat(search1).isEqualTo(0);
        assertThat(search3).isEqualTo(2);
    }
}