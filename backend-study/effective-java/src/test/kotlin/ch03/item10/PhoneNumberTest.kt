package ch03.item10

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PhoneNumberTest {

    @Test
    fun 비교자_테스트() {
        // given
        val phone1 = PhoneNumber(1, 1, 1)
        val phone2 = PhoneNumber(1, 2, 2)
        val phone3 = PhoneNumber(1, 2, 2)
        // when
        val result = phone1.compareTo(phone2)
        val result2 = phone2.compareTo(phone3)
        //then
        assertThat(result).isEqualTo(-1)
        assertThat(result2).isEqualTo(0)
    }
}