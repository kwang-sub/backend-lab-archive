package ch02.item05.ex02

import java.util.function.Supplier

class Mosaic {


    companion object {
        fun create(tileFactory: Supplier<out Tiler>): Mosaic {
            tileFactory.get()
            TODO()
        }
    }
}