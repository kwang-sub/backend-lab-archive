package modernjavainaction.study.ch10

import java.util.function.Function
import java.util.stream.Collector
import java.util.stream.Collectors


class GroupingBuilder<T, D, K>(
    private val collector: Collector<in T, *, Map<K, D>>
) {
    fun get() = collector

    fun <J> after(classifier: Function<in T, out J>): GroupingBuilder<T, Map<K, D>, J> {
        return GroupingBuilder(Collectors.groupingBy(classifier, collector))
    }

    companion object {
        fun <T, D, K> groupOn(classifier: Function<in T, out K>): GroupingBuilder<T, List<T>, K> {
            return GroupingBuilder(Collectors.groupingBy(classifier))
        }
    }
}


fun main() {
    val collector = GroupingBuilder.groupOn<Int, Double, Int> { it + 1 }.after<String> { it.toString() }.get()
    val collect = listOf(1, 2, 3)
        .stream()
        .collect(collector)
    println(collect)
}