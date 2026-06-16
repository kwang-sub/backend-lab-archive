package ch01

import ch01.ja.CalzoneJava
import ch01.ja.NutritionFactsJava
import ch01.ja.NyPizzaJavaJava
import ch01.ja.PizzaJava
import ch01.ko.NutritionFacts
import ch01.ko.NyPizza
import ch01.ko.Person
import ch01.ko.Size
import ch01.ko.Topping

fun main() {
    val person = Person.create("kwang", 30, "Student")
    println(person)

    val nutritionFactsJava = NutritionFactsJava.Builder(240, 9)
        .calories(100)
        .sodium(35)
        .build()

    val nutritionFacts = NutritionFacts()
        .apply {
            this.servingSize = 240
            this.servings = 8
            this.calories = 100
            this.sodium = 35
        }

    println("java version $nutritionFactsJava")
    println("kotlin version $nutritionFacts")

    val nyPizzaJava = NyPizzaJavaJava.Builder(NyPizzaJavaJava.Size.SMALL)
        .addTopping(PizzaJava.Topping.SAUSAGE).build()
    val calzoneJava = CalzoneJava.Builder()
        .sauceInside()
        .addTopping(PizzaJava.Topping.HAM)
        .build()
    println("java calzone $calzoneJava")
    println("java nyPizza $nyPizzaJava")

    println("================")

    val nyPizza = NyPizza.Builder()
        .setSize(Size.LARGE)
        .addTopping(Topping.HAM)
        .build()
    println(nyPizza)


}