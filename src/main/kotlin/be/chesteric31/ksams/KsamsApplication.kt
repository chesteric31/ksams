package be.chesteric31.ksams

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class KsamsApplication

fun main(args: Array<String>) {
    runApplication<KsamsApplication>(*args)
}
