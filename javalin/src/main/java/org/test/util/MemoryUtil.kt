package org.test.util

fun memoryCheck() {
    val runtime = Runtime.getRuntime()
    val freeMemoryMB = runtime.freeMemory() / (1024.0 * 1024.0)
    val totalMemoryMB = runtime.totalMemory() / (1024.0 * 1024.0)
    val maxMemoryMB = runtime.maxMemory() / (1024.0 * 1024.0)

    println("Free Memory: ${freeMemoryMB} MB")
    println("Used Memory ${totalMemoryMB - freeMemoryMB}")
    println("Total Memory: ${totalMemoryMB} MB")
    println("Max Memory: ${maxMemoryMB} MB")
    println("pct ${(totalMemoryMB - freeMemoryMB) * 100 / maxMemoryMB}")
}