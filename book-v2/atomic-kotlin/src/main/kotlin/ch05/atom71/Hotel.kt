package ch05.atom71

class Hotel(private val reception: String) {
    open inner class Room(val id: Int = 0) {
        fun callReception() = "Room $id Calling $reception"
    }
    private inner class Closet: Room()
    fun closet(): Room = Closet()
}

fun main() {
    val nycHotel = Hotel("311")
    val room = nycHotel.Room(319)
    println(room.callReception())
    val sfHotel = Hotel("0")
    val closet = sfHotel.closet()
    println(closet.callReception())
}