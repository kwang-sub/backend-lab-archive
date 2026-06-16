package ch15

class ComparisonCompactor (
    val fContextLength: Int? = null,
    val fExpected: String? = null,
    val fActual: String? = null,
    var fPrefix: Int,
    var fSuffix: Int,
) {

    fun compact(message: String): String {
        if (fExpected == null || fActual == null || areStringsEqual()) {
            return Assert
        }
    }

    companion object {
        const val ELLIPSIS: String = "..."
        const val DELTA_END: String = "]"
        const val DELTA_START: String = "["
    }
}