package ch07.atom81

fun <T> gFunction(arg: T): T = arg

class GClass<T>(val x: T) {
    fun f(): T = x
}

class GMemberFunction {
    fun <T> f(arg: T): T = arg
}

interface GInterface<T> {
    val x: T
    fun f(): T
}

class GImplementation<T>(
    override val x: T
) : GInterface<T> {
    override fun f(): T = x
}

class ConcreteImplementation : GInterface<String> {
    override val x: String
        get() = "x"

    override fun f(): String = "f()"
}

fun basicGenerics() {
    gFunction("Yello")
    gFunction(1)
    gFunction(Dog()).bark()
    gFunction<Dog>(Dog()).bark()

    GClass("Cyan").f()
    GClass(11).f()
    GClass(Dog()).f().bark()
    GClass<Dog>(Dog()).f().bark()

    GMemberFunction().f("Amber")
    GMemberFunction().f(111)
    GMemberFunction().f(Dog()).bark()
    GMemberFunction().f<Dog>(Dog()).bark()

    GImplementation<Dog>(Dog()).f().bark()

}