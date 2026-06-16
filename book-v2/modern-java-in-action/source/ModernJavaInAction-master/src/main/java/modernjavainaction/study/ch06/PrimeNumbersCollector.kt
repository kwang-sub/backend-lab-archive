package modernjavainaction.study.ch06

import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector
import kotlin.math.sqrt

class PrimeNumbersCollector :
    Collector<Int, Map<Boolean, MutableList<Int>>, Map<Boolean, MutableList<Int>>> {

    override fun supplier(): Supplier<Map<Boolean, MutableList<Int>>> {
        return Supplier {
            hashMapOf(
                true to mutableListOf(),
                false to mutableListOf()
            )
        }
    }

    override fun accumulator(): BiConsumer<Map<Boolean, MutableList<Int>>, Int> {
        return BiConsumer { acc, candidate ->
            val trueList = acc[true]?.let { isPrime(it, candidate) }
            acc[trueList]?.add(candidate)
        }
    }

    override fun combiner(): BinaryOperator<Map<Boolean, MutableList<Int>>> {
        return BinaryOperator { map1, map2 ->
            map2[true]?.let { map1[true]?.addAll(it) }
            map2[false]?.let { map1[false]?.addAll(it) }
            map1
        }
    }

    override fun finisher(): Function<Map<Boolean, MutableList<Int>>, Map<Boolean, MutableList<Int>>> {
        return Function.identity()
    }

    override fun characteristics(): MutableSet<Collector.Characteristics> {
        return mutableSetOf(Collector.Characteristics.IDENTITY_FINISH)
    }

    fun isPrime(primes: MutableList<Int>, candidate: Int): Boolean {
        val candidateRoot: Int = sqrt(candidate.toDouble()).toInt()
        return primes.takeWhile { it <= candidateRoot }
            .none { candidate % it == 0 }
    }
}