package ch05.item28

import java.util.concurrent.ThreadLocalRandom

class Chooser<T> (
    private val choiceArray: MutableList<out T>
) {

    fun choose(): T {
        val rnd = ThreadLocalRandom.current()
        return choiceArray[rnd.nextInt(choiceArray.size)]
    }
}
