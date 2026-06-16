package book.chap04.question;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    @Test
    void basicTest() {
        Deck deck = new Deck(6);
        deck.enqueFront(10);
        assertThat(deck.getNum()).isEqualTo(1);
        assertThat(deck.getFront()).isEqualTo(5);
        assertThat(deck.getRear()).isEqualTo(0);
        deck.enqueRear(9);
        assertThat(deck.getNum()).isEqualTo(2);
        assertThat(deck.getFront()).isEqualTo(5);
        assertThat(deck.getRear()).isEqualTo(1);
        deck.enqueFront(1);
        assertThat(deck.getNum()).isEqualTo(3);
        assertThat(deck.getFront()).isEqualTo(4);
        assertThat(deck.getRear()).isEqualTo(1);
        deck.enqueFront(2);
        assertThat(deck.getNum()).isEqualTo(4);
        assertThat(deck.getFront()).isEqualTo(3);
        assertThat(deck.getRear()).isEqualTo(1);
        int rear = deck.dequeRear();
        assertThat(rear).isEqualTo(9);
        assertThat(deck.getFront()).isEqualTo(3);
        assertThat(deck.getRear()).isEqualTo(0);
    }
}