package ja.ch04;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntDequeTest {
    private IntDeque deque;

    @BeforeEach
    void setUp() {
        deque = new IntDeque(5);
    }

    @Test
    void test() {
        deque.enqueueFr(1);
        deque.enqueueFr(2);
        deque.enqueueFr(3);
        deque.enqueueFr(4);
        deque.enqueueFr(5);
        deque.dequeueFr();

        deque.enqueueRe(6);
        assertThat(deque.dequeueRe()).isEqualTo(6);
        assertThat(deque.dequeueFr()).isEqualTo(4);
    }

    @Test
    void enqueueFr_front에_엘리먼트_추가() {
        deque.enqueueFr(10);
        assertEquals(10, deque.dequeueFr());
    }

    @Test
    void enqueueRe_rear에_엘리먼트_추가() {
        deque.enqueueRe(20);
        assertEquals(20, deque.dequeueRe());
    }

    @Test
    void enqueue_가득_찼을_때_마이너스_1_반환() {
        deque.enqueueFr(1);
        deque.enqueueFr(2);
        deque.enqueueFr(3);
        deque.enqueueRe(4);
        deque.enqueueRe(5);
        assertEquals(-1, deque.enqueueRe(6));
    }

    @Test
    void dequeueFront_front에서_엘리먼트_제거() {
        deque.enqueueFr(10);
        assertEquals(10, deque.dequeueFr());
    }

    @Test
    void dequeueRear_rear에서_엘리먼트_제거() {
        deque.enqueueRe(20);
        assertEquals(20, deque.dequeueRe());
    }

    @Test
    void dequeueFront_비어있을_때_마이너스_1_반환() {
        assertEquals(-1, deque.dequeueFr());
    }

    @Test
    void dequeueRear_비어있을_때_마이너스_1_반환() {
        assertEquals(-1, deque.dequeueRe());
    }

    @Test
    void enqueueFrontAndDequeueFront_FIFO_유지() {
        deque.enqueueFr(10);
        deque.enqueueFr(20);
        deque.dequeueRe();
        assertEquals(20, deque.dequeueFr());
    }

    @Test
    void enqueueRearAndDequeueRear_LIFO_유지() {
        deque.enqueueRe(30);
        deque.enqueueRe(40);
        deque.dequeueFr();
        assertEquals(40, deque.dequeueRe());
    }

    @Test
    void enqueueFrontAndDequeueRear_엘리먼트_반환하지_않음() {
        deque.enqueueFr(50);
        assertEquals(50, deque.dequeueRe());
    }

    @Test
    void enqueueRearAndDequeueFront_엘리먼트_반환하지_않음() {
        deque.enqueueRe(60);
        assertEquals(60, deque.dequeueFr());
    }

    @Test
    void capacity_초기화_정상() {
        IntDeque deque2 = new IntDeque(7);
        deque2.enqueueFr(1);
        deque2.enqueueFr(2);
        deque2.enqueueFr(3);
        deque2.enqueueRe(4);
        deque2.enqueueRe(5);
        deque2.enqueueRe(6);
        deque2.enqueueRe(7);
        assertEquals(-1, deque2.enqueueRe(8));
    }

    @Test
    void front와_rear가_정상적으로_순환() {
        deque.enqueueFr(1);
        deque.enqueueFr(2);
        deque.enqueueFr(3);
        deque.enqueueRe(4);
        deque.dequeueFr();
        deque.dequeueFr();
        deque.enqueueRe(5);
        deque.enqueueRe(6);
        assertEquals(6, deque.dequeueRe());
    }
}