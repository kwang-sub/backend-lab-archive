package ch04

class User3(
    val name: String
) {
    var address: String = "unspecified"
        set(value: String) {
            println("""
                Address was changed for $name: "$field" -> "$value".
            """.trimIndent())
            field = value
        }

    var test: String? = null
}