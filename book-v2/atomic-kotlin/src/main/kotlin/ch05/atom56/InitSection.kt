package ch05.atom56

private var counter = 0

class Message(text: String) {
    private val content: String
    init {
        counter += 10
        content = "[$counter] $text"
    }

    override fun toString(): String {
        return "Message(content='$content')"
    }
}

fun main() {
    println(Message("test"))
    println(Message("test"))
    println(Message("test"))
}