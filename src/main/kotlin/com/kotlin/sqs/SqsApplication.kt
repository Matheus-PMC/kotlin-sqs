package com.kotlin.sqs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ConfigurationPropertiesScan
class SqsApplication

fun main(args: Array<String>) {
    runApplication<SqsApplication>(*args)
}
