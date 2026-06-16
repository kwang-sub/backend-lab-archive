package ja.ch01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Max3DualStackTest {
    @Test
    void 최대값테스트() {
        // given
        Max3 max3 = new Max3();
        // when
        int result = max3.max3(3, 2, 3);
        // then
        assertThat(result).isEqualTo(3);
    }
}