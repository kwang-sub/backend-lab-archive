package modernjavainaction.study.ch10

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val errors = Files.lines(Paths.get("test"))
        .filter { it.startsWith("ERROR") }
        .limit(40)
}