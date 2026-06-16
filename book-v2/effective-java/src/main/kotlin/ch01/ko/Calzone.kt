package ch01.ko

sealed class Source {
    object ASource : Source()
    object BSource : Source()
}

class Calzone : Pizza() {
    private val sourceLists = arrayListOf<Source>()

    fun setSourceList(list: List<Source>) {
        sourceLists.clear()
        sourceLists.addAll(list)
    }

    class Builder : Pizza.Builder() {
        private val sourceLists = arrayListOf<Source>()

        fun addSource(source: Source): Pizza.Builder {
            sourceLists.add(source)
            return this
        }

        override fun build(): Pizza = Calzone().apply {
            setSourceList(sourceLists)
            setToppingList(toppingList)
        }
    }

}
