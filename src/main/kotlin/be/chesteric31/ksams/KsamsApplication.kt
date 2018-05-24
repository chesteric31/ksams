package be.chesteric31.ksams

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KsamsApplication

fun main(args: Array<String>) {
    runApplication<KsamsApplication>(*args)
}
