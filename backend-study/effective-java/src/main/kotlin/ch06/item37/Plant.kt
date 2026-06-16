package ch06.item37

import java.util.EnumMap

class Plant(
    val name: String,
    val lifeCycle: LifeCycle,
) {
    enum class LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    override fun toString(): String {
        return name
    }
}

fun main() {
    val plantsByLifeCycle: EnumMap<Plant.LifeCycle, MutableSet<Plant>> = EnumMap(Plant.LifeCycle::class.java)
    for (lc: Plant.LifeCycle in Plant.LifeCycle.entries) {
        plantsByLifeCycle[lc] = mutableSetOf()
    }
    val garden = listOf(
        Plant("2", Plant.LifeCycle.PERENNIAL),
        Plant("1", Plant.LifeCycle.PERENNIAL),
        Plant("2", Plant.LifeCycle.ANNUAL)
    )
    for (plant in garden) {
        plantsByLifeCycle[plant.lifeCycle]?.add(plant)
    }
    println(plantsByLifeCycle)
    println(EnumMap<Plant.LifeCycle, Any>(garden.groupBy { it.lifeCycle }))
}