package ch05.atom72

interface ZI {
    fun f(): String
    fun g(): String
}

open class ZIOpen : ZI {
    override fun f(): String {
        return "ZIOpen.f()"
    }

    override fun g(): String {
        return "ZIOpen.g()"
    }
}

class ZiCompanion {
    companion object : ZIOpen()

    fun u() = "${f()} ${g()}"
}

class ZICompanionInheritance {
    companion object : ZIOpen() {
        override fun f(): String {
            return "ZI.f()"
        }

        override fun g(): String {
            return "ZI.g()"
        }
    }
}

class ZIClass {
    companion object : ZI by ZIOpen() {

    }
}