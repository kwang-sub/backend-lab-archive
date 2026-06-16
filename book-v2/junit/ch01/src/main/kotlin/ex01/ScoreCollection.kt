package ex01

class ScoreCollection {
    private val scores: MutableList<Scoreable> = mutableListOf()

    fun add(scoreable: Scoreable) {
        scores.add(scoreable)
    }

    fun arithmeticMean(): Int {
        return scores.sumOf { it.getScore() } / scores.size
    }
}