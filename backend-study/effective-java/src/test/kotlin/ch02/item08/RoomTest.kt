package ch02.item08

import ch02.item08.Room
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream


class RoomTest {
    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun room_청소_확인() {
        // given
        try {
            val myRoom: Room = Room(6)
        // when
            myRoom.use { println("안녕~") }
        } catch (e: Exception) { }
        //then
        assertThat(outputStreamCaptor.toString().trim()).contains("방 청소")
    }

    @Test
    fun room_청소_안함_확인() {
        // given
        val result = "청소 안함"
        val room = Room(10)
        // when
        println(result)
        //then
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(result)
    }
}