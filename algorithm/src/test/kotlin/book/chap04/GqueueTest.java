package book.chap04;

import book.chap04.question.Gqueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GqueueTest {

    private Gqueue<Integer> intQueue;

    @BeforeEach
    void init() {
        intQueue = new Gqueue<>(3);
    }

    @Test
    void enqueTest() {
        int enque1 = intQueue.enque(1);
        int enque2 = intQueue.enque(2);
        int deque1 = intQueue.deque();
        int deque2 = intQueue.deque();
        int enque3 = intQueue.enque(3);
        int enque4 = intQueue.enque(4);

        assertThat(enque2).isEqualTo(2);
        assertThat(deque1).isEqualTo(1);
        assertThat(deque2).isEqualTo(2);
        assertThat(enque4).isEqualTo(4);
    }

}