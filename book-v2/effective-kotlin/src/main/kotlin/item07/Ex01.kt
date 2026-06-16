package item07

fun main() {
    val readObjectOrNull = "t".readObjectOrNull<String>(true)


    when (readObjectOrNull) {
        is Success -> println("성공")
        is Failure -> println("실패")
    }
}


inline fun <reified T> String.readObjectOrNull(check: Boolean): Result<T> {
    if (check) {
        return Failure(NoSuchElementException())

    }
    return Success("as" as T)
}

sealed class Result<out T>
class Success<out T>(val result: T): Result<T>()
class Failure(val throwable: Throwable): Result<Nothing>()