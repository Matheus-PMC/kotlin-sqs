package com.kotlin.sqs.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "provider")
class ProviderProperties {
    lateinit var url: String
}