package com.recipyapp.recipyserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RecipyServerApplication

fun main(args: Array<String>) {
    runApplication<RecipyServerApplication>(*args)
}
