package ex02

abstract class Question(
    private var text: String,
    private var answerChoices: Array<String>,
    private var id: Int,
) {
    fun getText() = text

    fun getAnswerChoice(i: Int) = answerChoices[i]

    open fun match(answer: Answer) = false

    abstract fun match(expected: Int, actual: Int): Boolean

    fun indexOf(matchingAnswerChoice: String): Int {
        for (i in answerChoices.indices) {
            if (answerChoices[i] == matchingAnswerChoice) return i
        }
        return -1
    }
}




