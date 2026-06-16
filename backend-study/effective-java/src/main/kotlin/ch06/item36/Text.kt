package ch06.item36

import java.util.EnumSet

class Text {
    enum class Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

    fun applyStyles(styles: Set<Style>) {

    }
}

fun main() {
    val text = Text()
    text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC))
}