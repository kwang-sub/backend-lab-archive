package ch02.item09

import java.io.*

fun firstLineOfFile(path: String, defaultVal: String): String {
    val br = BufferedReader(FileReader(path))
    try {
        return br.use { it.readLine() }
    } catch (e: IOException) {
        return defaultVal
    }
}

fun copy(src: String, dst: String) {
    val byte = byteArrayOf()
    var n: Int = 0
    do {
        n = FileInputStream(src).use { it.read(byte) }
        FileOutputStream(dst).use { it.write(byte, 0, n) }
    } while (n >= 0)
}
