package ch02.atom22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalculateBMITest {
    @Test
    fun BMI_테스트() {
        // given
        val normal = calculateBMI(160.0, 68.0)
        val under = calculateBMI(100.0, 68.0)
        val over = calculateBMI(200.0, 68.0)
        // when

        //then
        assertThat(normal).isEqualTo("Normal weight")
        assertThat(under).isEqualTo("Underweight")
        assertThat(over).isEqualTo("Overweight")
    }
}

fun calculateBMI(weight: Double, height: Double): String {
    val bmi = weight / (height * height) * 703.07
    return if (bmi < 18.5) "Underweight"
    else if (bmi < 25) "Normal weight"
    else "Overweight"
}