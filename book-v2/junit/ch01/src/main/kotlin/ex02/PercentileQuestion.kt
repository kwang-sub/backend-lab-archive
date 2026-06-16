package ex02

class PercentileQuestion(
    var id: Int,
    var title: String,
    var answerChoices: Array<String>
) : Question(title, answerChoices, id) {

    override fun match(expected: Int, actual: Int): Boolean = expected <= actual
}