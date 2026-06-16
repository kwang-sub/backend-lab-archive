package ch05.atom71

class Fruit {
    fun changeColor(color: String) = "Fruit $color"
    fun absorbWater(amount: Int) {}
    inner class Seed {
        fun changeColor(color: String) = "Seed $color"
        fun germinate(){}
        fun whichThis() {
        }
    }
}