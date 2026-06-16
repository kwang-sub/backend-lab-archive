package ch04.tags.junit5;

import ch04.Calculator;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JUnit5ExceptionTest {

    private Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
        AbstractThrowableAssert<?, ? extends Throwable> abstractThrowableAssert = assertThatThrownBy(() -> calculator.sqrt(-1));

        abstractThrowableAssert.isInstanceOf(IllegalArgumentException.class);
        abstractThrowableAssert.message().isEqualTo("음수의 제곱근을 구할 수 없다");
    }
}