package book.chap04.question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DoubleIntStackTest {

    private DoubleIntStack stack;

    @BeforeEach
    void init() {
        stack = new DoubleIntStack(3);
    }

    @Test
    void pushTest() {
        int push = stack.push(10, DoubleIntStack.Direction.LEFT);
        int pushRight = stack.push(12, DoubleIntStack.Direction.RIGHT);
        stack.push(10, DoubleIntStack.Direction.LEFT);
        assertThat(push).isEqualTo(10);
        assertThat(pushRight).isEqualTo(12);

        assertThrows(DoubleIntStack.OverflowIntStackException.class, () -> stack.push(109, DoubleIntStack.Direction.RIGHT));
    }

    @Test
    void rightPushTest() {
        int push1 = stack.push(1, DoubleIntStack.Direction.RIGHT);
        int push2 = stack.push(2, DoubleIntStack.Direction.RIGHT);
        int push3 = stack.push(3, DoubleIntStack.Direction.RIGHT);

        assertThat(push1).isEqualTo(1);
        assertThat(push2).isEqualTo(2);
        assertThat(push3).isEqualTo(3);
        assertThrows(DoubleIntStack.OverflowIntStackException.class, () -> stack.push(109, DoubleIntStack.Direction.LEFT));
        assertThrows(DoubleIntStack.OverflowIntStackException.class, () -> stack.push(109, DoubleIntStack.Direction.RIGHT));
    }

    @Test
    void leftPushTest() {
        int push1 = stack.push(1, DoubleIntStack.Direction.LEFT);
        int push2 = stack.push(2, DoubleIntStack.Direction.LEFT);
        int push3 = stack.push(3, DoubleIntStack.Direction.LEFT);

        assertThat(push1).isEqualTo(1);
        assertThat(push2).isEqualTo(2);
        assertThat(push3).isEqualTo(3);
        assertThrows(DoubleIntStack.OverflowIntStackException.class, () -> stack.push(109, DoubleIntStack.Direction.RIGHT));
        assertThrows(DoubleIntStack.OverflowIntStackException.class, () -> stack.push(109, DoubleIntStack.Direction.LEFT));
    }

    @Test
    void leftPopTest() {
        stack.push(1, DoubleIntStack.Direction.LEFT);

        int pop = stack.pop(DoubleIntStack.Direction.LEFT);

        assertThat(pop).isEqualTo(1);
        assertThrows(DoubleIntStack.EmptyIntStackException.class, () -> stack.pop(DoubleIntStack.Direction.RIGHT));
        assertThrows(DoubleIntStack.EmptyIntStackException.class, () -> stack.pop(DoubleIntStack.Direction.LEFT));
    }

    @Test
    void rightPopTest() {
        stack.push(1, DoubleIntStack.Direction.RIGHT);

        int pop = stack.pop(DoubleIntStack.Direction.RIGHT);

        assertThat(pop).isEqualTo(1);
        assertThrows(DoubleIntStack.EmptyIntStackException.class, () -> stack.pop(DoubleIntStack.Direction.RIGHT));
        assertThrows(DoubleIntStack.EmptyIntStackException.class, () -> stack.pop(DoubleIntStack.Direction.LEFT));
    }

}