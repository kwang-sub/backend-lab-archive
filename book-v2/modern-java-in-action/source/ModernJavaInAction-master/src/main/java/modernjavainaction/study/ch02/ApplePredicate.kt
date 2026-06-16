package modernjavainaction.study.ch02

import modernjavainaction.base.chap02.FilteringApples
import modernjavainaction.base.chap02.FilteringApples.Apple

interface ApplePredicate {
    fun test(apple: Apple): Boolean
}

class AppleHeavyWeightPredicate : ApplePredicate {
    override fun test(apple: FilteringApples.Apple): Boolean {
        return apple.weight > 150
    }
}

class AppleGreenColorPredicate : ApplePredicate {
    override fun test(apple: FilteringApples.Apple): Boolean {
        return apple.color == FilteringApples.Color.GREEN
    }
}