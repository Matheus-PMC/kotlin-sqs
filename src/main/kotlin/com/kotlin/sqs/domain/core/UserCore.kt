package com.kotlin.sqs.domain.core

import com.kotlin.sqs.configuration.ProviderProperties
import com.kotlin.sqs.domain.model.UserLoginRequest
import com.kotlin.sqs.domain.model.TokenResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class UserCore(
    private val restTemplate: RestTemplate,
    private val providerProperties: ProviderProperties
) {

    fun login(userLoginRequest: UserLoginRequest): TokenResponse {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
        }

        val entity = HttpEntity(userLoginRequest.toBody(), headers)
        val response = restTemplate.exchange(providerProperties.url, HttpMethod.POST, entity, TokenResponse::class.java)

        return response.body ?: throw RuntimeException("Usuario incorreto!")
    }
}