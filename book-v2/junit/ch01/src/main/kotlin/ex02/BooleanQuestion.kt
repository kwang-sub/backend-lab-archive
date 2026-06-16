package ex02

data class BooleanQuestion(
    var id: Int,
    var title: String
) : Question(id = id, text = title, answerChoices = arrayOf("NO", "YES")) {

    override fun match(expected: Int, actual: Int): Boolean = expected == actual
}