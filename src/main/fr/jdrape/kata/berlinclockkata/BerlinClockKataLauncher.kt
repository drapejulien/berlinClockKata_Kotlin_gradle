package fr.jdrape.kata.berlinclockkata

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = arrayOf("fr.jdrape.kata.berlinclockkata"))
class BerlinClockKataLauncher

fun main(args: Array<String>) {
    SpringApplication.run(BerlinClockKataLauncher::class.java, *args)
}


