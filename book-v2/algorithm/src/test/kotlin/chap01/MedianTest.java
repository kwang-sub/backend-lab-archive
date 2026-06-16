package chap01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MedianTest {

    @Test
    void medianTest() {
        int result = Median.med3(1, 56, 4);
        assertThat(result).isEqualTo(4);

        int result1 = Median.med3(1, 56, 77);
        assertThat(result1).isEqualTo(56);

        int result2 = Median.med3(1, 6, 4);
        assertThat(result2).isEqualTo(4);

        int result3 = Median.med3(1, 56, 42);
        assertThat(result3).isEqualTo(42);
    }

}