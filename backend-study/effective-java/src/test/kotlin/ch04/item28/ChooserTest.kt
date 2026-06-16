package ch04.item28

import ch05.item28.Chooser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChooserTest {


    @Test
    fun 테스트() {
        // given
        val choiceArray = mutableListOf(1, 2, 3, 4)
        val chooser = Chooser(choiceArray)
        // when
        val choose = chooser.choose()
        //then
        assertThat(choose).isIn(choiceArray)
    }
}