package item01

/**
 * val를 var로 오버라이딩 가능
 * var는 val로 오버라이딩 불가능
 * @property active Boolean
 */

interface Element {
    val active: Boolean
    var test: Boolean
}

class ActualElement : Element {
    override var active: Boolean = false
    override var test: Boolean = true
}