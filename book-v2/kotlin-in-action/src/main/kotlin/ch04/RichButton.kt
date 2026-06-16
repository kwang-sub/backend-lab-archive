package ch04

open class RichButton : Clickable {

    fun disable() {}

    open fun animate() {}

    final override fun click() {}
}