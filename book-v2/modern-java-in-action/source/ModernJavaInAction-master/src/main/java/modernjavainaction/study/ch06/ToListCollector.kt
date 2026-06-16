package modernjavainaction.study.ch06

import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector

class ToListCollector<T> : Collector<T, MutableList<T>, MutableList<T>> {
    override fun supplier(): Supplier<MutableList<T>> {
        return Supplier { mutableListOf<T>() }
    }

    override fun accumulator(): BiConsumer<MutableList<T>, T> {
        return BiConsumer { list, item -> list.add(item) }
    }

    override fun combiner(): BinaryOperator<MutableList<T>> {
        return BinaryOperator { list1, list2 ->
            list1.addAll(list2)
            list1
        }
    }

    override fun finisher(): Function<MutableList<T>, MutableList<T>> {
        return Function.identity()
    }

    override fun characteristics(): MutableSet<Collector.Characteristics> {
        return mutableSetOf(Collector.Characteristics.IDENTITY_FINISH, Collector.Characteristics.CONCURRENT)
    }
}