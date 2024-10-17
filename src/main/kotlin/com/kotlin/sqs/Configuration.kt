package com.kotlin.sqs

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class Configuration(
    @Value("\${cloud.aws.queue.uri}") private var host: String,
    @Value("\${cloud.aws.credentials.access-key}") private var accessKeyId: String,
    @Value("\${cloud.aws.credentials.secret-key}") private var secretAccessKey: String,
    @Value("\${cloud.aws.region.static}") private var region: String
) {

    @Bean
    @Primary
    fun amazonSQSAsync(): AmazonSQSAsync {
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(getEndpointConfiguration(host, region))
            .withCredentials(getCredentialsProvider(accessKeyId, secretAccessKey))
            .build()
    }

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate {
        return QueueMessagingTemplate(amazonSQSAsync())
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }

    companion object {
        fun getEndpointConfiguration(host: String, region: String): AwsClientBuilder.EndpointConfiguration {
            return AwsClientBuilder.EndpointConfiguration(host, region)
        }

        fun getCredentialsProvider(accessKeyId: String, secretAccessKey: String): AWSStaticCredentialsProvider {
            return AWSStaticCredentialsProvider(BasicAWSCredentials(accessKeyId, secretAccessKey))
        }
    }
}