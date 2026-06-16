package ja.ch04;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntAryQueueTest {

    @Test
    void test() {
        IntAryQueue q = new IntAryQueue(10);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);
        q.enqueue(10);

        assertThat(q.dequeue()).isEqualTo(1);
        assertThat(q.dequeue()).isEqualTo(2);
        assertThat(q.dequeue()).isEqualTo(3);
        assertThat(q.dequeue()).isEqualTo(4);
        assertThat(q.dequeue()).isEqualTo(5);
        assertThat(q.dequeue()).isEqualTo(6);
        assertThat(q.dequeue()).isEqualTo(7);
        assertThat(q.dequeue()).isEqualTo(8);
        assertThat(q.dequeue()).isEqualTo(9);
        assertThat(q.dequeue()).isEqualTo(10);
        q.enqueue(12);
        assertThat(q.dequeue()).isEqualTo(12);
        assertThatThrownBy(() -> q.dequeue()).isInstanceOf(IntAryQueue.EmptyQueException.class);
    }


    @Test
    void test2() {
        IntQueue q = new IntQueue(10);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);
        q.enqueue(10);

        assertThatThrownBy(() -> q.enqueue(11)).isInstanceOf(IntQueue.OverflowQueException.class);
        assertThat(q.dequeue()).isEqualTo(1);
        assertThat(q.dequeue()).isEqualTo(2);
        assertThat(q.dequeue()).isEqualTo(3);
        assertThat(q.indexOf(10)).isEqualTo(9);
        assertThat(q.dequeue()).isEqualTo(4);
        assertThat(q.dequeue()).isEqualTo(5);
        assertThat(q.dequeue()).isEqualTo(6);
        assertThat(q.dequeue()).isEqualTo(7);
        q.dump();
        assertThat(q.dequeue()).isEqualTo(8);
        assertThat(q.dequeue()).isEqualTo(9);
        assertThat(q.dequeue()).isEqualTo(10);
        q.enqueue(12);
        assertThat(q.dequeue()).isEqualTo(12);
        assertThatThrownBy(() -> q.dequeue()).isInstanceOf(IntQueue.EmptyQueException.class);
    }


    @Test
    void searchTest() {
        IntQueue q = new IntQueue(10);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.enqueue(7);
        q.enqueue(8);
        q.enqueue(9);
        q.enqueue(10);

        q.dequeue();

        assertThat(q.search(4)).isEqualTo(3);
        assertThat(q.search(1)).isEqualTo(0);
    }
}