package ch07

import kotlin.reflect.KProperty

class Foo {
    var p : String by Delegate()

}

class Delegate {

    var pp: String = "test"

    operator fun getValue(foo: Foo, property: KProperty<*>): String {
        return pp
    }

    operator fun setValue(foo: Foo, property: KProperty<*>, s: String) {
        pp = s
    }
}