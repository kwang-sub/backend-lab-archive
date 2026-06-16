package modernjavainaction.study.ch15

import java.util.concurrent.Flow
import java.util.function.Consumer

open class SimpleCell(private val name: String) : Flow.Publisher<Int>,
    Flow.Subscriber<Int> {
    private var value = 0
    private val subscribers: MutableList<Flow.Subscriber<in Int>> = ArrayList()

    override fun subscribe(subscriber: Flow.Subscriber<in Int>) {
        subscribers.add(subscriber)
    }

    fun subscribe(onNext: Consumer<in Int?>) {
        subscribers.add(object : Flow.Subscriber<Int?> {
            override fun onComplete() {}

            override fun onError(t: Throwable) {
                t.printStackTrace()
            }

            override fun onNext(`val`: Int?) {
                onNext.accept(`val`)
            }

            override fun onSubscribe(s: Flow.Subscription) {}
        })
    }

    private fun notifyAllSubscribers() {
        subscribers.forEach(Consumer { subscriber: Flow.Subscriber<in Int> ->
            subscriber.onNext(
                value
            )
        })
    }

    override fun onNext(newValue: Int) {
        value = newValue
        println("$name:$value")
        notifyAllSubscribers()
    }

    override fun onComplete() {}

    override fun onError(t: Throwable) {
        t.printStackTrace()
    }

    override fun onSubscribe(s: Flow.Subscription) {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val c3 = SimpleCell("C3")
            val c2 = SimpleCell("C2")
            val c1 = SimpleCell("C1")

            c1.subscribe(c3)

            c1.onNext(10) // C1의 값을 10으로 갱신
            c2.onNext(20) // C2의 값을 20으로 갱신
        }
    }
}


class ArithmeticCell(name: String) : SimpleCell(name) {
    private var left = 0
    private var right = 0

    fun setLeft(left: Int) {
        this.left = left
        onNext(left + right)
    }

    fun setRight(right: Int) {
        this.right = right
        onNext(right + left)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            test1()
            println("------------")
            test2()
        }

        private fun test1() {
            val c3 = ArithmeticCell("C3")
            val c2 = modernjavainaction.base.chap15.flow.SimpleCell("C2")
            val c1 = modernjavainaction.base.chap15.flow.SimpleCell("C1")

            c1.subscribe { left: Int -> c3.setLeft(left) }
            c2.subscribe { right: Int -> c3.setRight(right) }

            c1.onNext(10) // C1의 값을 10으로 갱신
            c2.onNext(20) // C2의 값을 20으로 갱
            c1.onNext(15) // C1의 값을 15로 갱신
        }

        private fun test2() {
            val c5 = ArithmeticCell("C5")
            val c3 = ArithmeticCell("C3")
            val c4 = modernjavainaction.base.chap15.flow.SimpleCell("C4")
            val c2 = modernjavainaction.base.chap15.flow.SimpleCell("C2")
            val c1 = modernjavainaction.base.chap15.flow.SimpleCell("C1")

            c1.subscribe { left: Int -> c3.setLeft(left) }
            c2.subscribe { right: Int -> c3.setRight(right) }

//            c3.subscribe { left: Int -> c5.setLeft(left) }
            c4.subscribe { right: Int -> c5.setRight(right) }

            c1.onNext(10) // C1의 값을 10으로 갱신
            c2.onNext(20) // C2의 값을 20으로 갱신
            c1.onNext(15) // C1의 값을 15로 갱신
            c4.onNext(1) // C4의 값을 1로 갱신
            c4.onNext(3) // C4의 값을 3으로 갱신
        }
    }
}
