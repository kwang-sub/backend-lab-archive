package ex02

class Person {

    private val characteristics = mutableListOf<Question>()

    fun add(characteristic: Question) = characteristics.add(characteristic)

    fun getCharacteristics(): MutableList<Question> = characteristics

    fun withCharacteristic(questionPattern: String): List<Question> =
        characteristics.filter { it.getText().endsWith(questionPattern) }

}