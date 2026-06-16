/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package ex02

import java.util.*
import kotlin.math.pow


class Criteria : Iterable<Criterion?> {

   private val criteria: MutableList<Criterion> = mutableListOf()

   fun add(criterion: Criterion) = criteria.add(criterion)

   override fun iterator(): MutableIterator<Criterion> = criteria.iterator()

   fun arithmeticMean(): Int = 0

   fun geometricMean(numbers: IntArray): Double {
       val totalProduct = Arrays.stream(numbers).reduce(1) { product: Int, number: Int -> product * number }
       return totalProduct.toDouble().pow(1.0 / numbers.size)
   }
}
