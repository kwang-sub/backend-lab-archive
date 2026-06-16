package ex02

data class Profile(
    var name: String,
    val answers: MutableMap<String, Answer> = mutableMapOf(),
    var score: Int = 0,
) {
    fun add(answer: Answer) {
        answer.getQuestionText()?.let { answers[it] = answer }
    }

    fun matches(criteria: Criteria): Boolean {
        score = 0
        var kill  = false
        var anyMatches = false
        for (criterion: Criterion in criteria) {
            val answer = answers[criterion.answer.getQuestionText()]
                    ?: throw RuntimeException("answer not found")
            val match = criterion.weight == Weight.DontCare || answer.match(criterion.answer)

            if (!match && criterion.weight == Weight.MustMatch) kill = true
            if (match) score += criterion.weight.value
            anyMatches = anyMatches or match
        }

        if (kill) return false
        return anyMatches
    }


}
