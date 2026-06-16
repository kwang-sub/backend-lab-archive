package ch07.atom81

interface Parent
interface Child: Parent

interface X {
    fun f(): Parent
}

interface Y : X {
    override fun f(): Child
}