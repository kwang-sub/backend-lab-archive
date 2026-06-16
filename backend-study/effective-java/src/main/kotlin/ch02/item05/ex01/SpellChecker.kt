package ch02.item05.ex01

class SpellChecker(
    private val _dictionary: Lexicon
) {
    val dictionary: Lexicon
        get() = _dictionary

    fun isValid(word: String) {

    }

    fun suggestions(typo: String): List<String> {
        TODO()
    }
}