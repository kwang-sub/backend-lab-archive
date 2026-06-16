package ch09

interface SampleList<T> {
    var list: List<T>

    operator fun get(index: Int): T
}

data class StringList(
    override var list: List<String>
): SampleList<String> {
    override fun get(index: Int): String {
        return list[index]
    }
}

data class ArrayList<N>  (
    override var list: List<N>
): SampleList<N> {
    override fun get(index: Int): N {
        TODO("Not yet implemented")
    }
}