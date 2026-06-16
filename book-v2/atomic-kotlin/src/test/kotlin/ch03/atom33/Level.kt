package ch03.atom33

enum class Level {
    Overflow, High, Medium, Low, Empty
}

fun checkLevel(level: Level) =
    when (level) {
        Level.Overflow -> "Overflow"
        Level.High -> "High"
        Level.Medium -> "Medium"
        Level.Low -> "Low"
        Level.Empty -> "Empty"
    }