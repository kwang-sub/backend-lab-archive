package ch02.atom22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestingExampleKtTest {

    @Test
    fun 기본_테스트() {
        // given
        val v1 = 11
        val v2 = "Ontology"
        // when

        //then
        assertThat(v1).isEqualTo(11)
        assertThat(v2).isEqualTo("Ontology")
        assertThat(v2).isNotEqualTo("Not")
    }
}