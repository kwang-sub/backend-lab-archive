package ex02

data class Answer(
    var question: Question? = null,
    var i: Int? = null,
) {


    fun Answer(characteristic: Question, matchingValue: String) {
        question = characteristic
        i = characteristic.indexOf(matchingValue)
    }

    fun getQuestionText() = question?.getText()

    fun match(expected: Int) = i?.let { question?.match(expected, it) }

    fun match(otherAnswer: Answer) = i?.let { otherAnswer.i?.let { it1 -> question?.match(it, it1) } } == true

    fun getCharacteristic() = question
}
