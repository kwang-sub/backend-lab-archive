package org.example.car

data class Car(
    val name: String,
    var distance: Int = 0
) {

    fun drive(engineNum: Int) {
        if (engineNum >= 4) distance++
    }
}