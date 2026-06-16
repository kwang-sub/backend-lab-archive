package ch04.atom47

data class Message(
    val sender: String,
    val text: String,
    val isRead: Boolean,
    val attachments: List<Attachment>
) {
}

data class Attachment(
    val type: String,
    val name: String,
)

fun Message.isImportant(): Boolean {
    return text.contains("Salary increase") || attachments.any { it.type == "image" && it.name.contains("cat") }
}

fun main() {
    val messages = listOf(
        Message("b", "b", true, listOf(Attachment("image", "cat"))),
        Message("Salary increase", "a", true, listOf(Attachment("image", "cat1"))),
        Message("a", "a", true, listOf(Attachment("image", "ca1"))),
        Message("c", "c", true, listOf(Attachment("image", "ca1"))),
        Message("d", "d", true, listOf(Attachment("image", "ca1"))),
        Message("e", "e", true, listOf(Attachment("image", "ca"))),
    )

    println(messages.filter(Message::isImportant))

}