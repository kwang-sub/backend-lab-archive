package modernjavainaction.study.ch03

import java.util.function.Function

class Letter {
    companion object {

        fun addHeader(text: String): String {
            return "From Raoul, Mario and Alan: $text"
        }

        fun addFooter(text: String): String {
            return "$text Kind regards"
        }

        fun checkSpelling(text: String): String {
            return text.replace("labda", "lambda")
        }
    }
}

fun main() {
    val addHeader = Function<String, String>(Letter::addHeader)
    val transformationPipeline = addHeader.andThen(Letter::checkSpelling)
        .andThen(Letter::addFooter)

    println(transformationPipeline.apply("hi labda"))
}