package org.example.ladder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LadderApplication

fun main(args: Array<String>) {
    runApplication<LadderApplication>(*args)
}
