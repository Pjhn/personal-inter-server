package com.pjhn.td

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TdApplication

fun main(args: Array<String>) {
	runApplication<TdApplication>(*args)
}
