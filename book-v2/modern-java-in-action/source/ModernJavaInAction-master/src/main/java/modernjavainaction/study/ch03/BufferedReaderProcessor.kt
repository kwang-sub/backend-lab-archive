package modernjavainaction.study.ch03

import java.io.BufferedReader

fun interface BufferedReaderProcessor {
    fun process(b: BufferedReader): String
}