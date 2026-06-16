package ch07.atom81

import kotlin.random.Random

private val rnd = Random(47)

fun List<Disposable>.aRandom(): Disposable = this[rnd.nextInt(size)]
