package ex02

enum class Bool(
    var value: Int
) {

    False(0), True(1);

    companion object {
        const val FALSE = 0
        const val TRUE = 1

    }
}


