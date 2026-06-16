package ch01.ko


enum class Size {
    SMALL, MEDIUM, LARGE

}

class NyPizza : Pizza() {
    lateinit var size: Size

    class Builder : Pizza.Builder() {
        private lateinit var size: Size

        fun setSize(size: Size): Pizza.Builder {
            this.size = size
            return this
        }

        override fun build(): Pizza {
            val builderSize = this.size

            return NyPizza()
                .apply { this.size = builderSize }
        }
    }

    override fun toString(): String {
        return super.toString() + "NyPizza(size=$size)"
    }


}