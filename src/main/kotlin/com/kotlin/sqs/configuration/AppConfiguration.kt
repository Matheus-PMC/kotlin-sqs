package com.kotlin.sqs.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.kotlinModule

@Configuration
class AppConfiguration {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModule(kotlinModule())
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    }
}