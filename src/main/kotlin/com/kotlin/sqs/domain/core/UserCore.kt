package com.kotlin.sqs.domain.core

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kotlin.sqs.configuration.ProviderProperties
import com.kotlin.sqs.domain.model.TokenResponse
import com.kotlin.sqs.domain.model.UserCreatedRequest
import com.kotlin.sqs.domain.model.UserLoginRequest
import com.kotlin.sqs.domain.model.UserLoginResponse
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange


@Component
class UserCore(
    private val restTemplate: RestTemplate,
    private val providerProperties: ProviderProperties
) {

    fun login(userLoginRequest: UserLoginRequest): UserLoginResponse {
        val token = token(userLoginRequest)
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
            setBearerAuth(token.accessToken)
        }

        val url = providerProperties.user + "?username=${userLoginRequest.username}"
        val entity = HttpEntity(userLoginRequest.toBody(), headers)
        val response: ResponseEntity<List<UserLoginResponse>> = restTemplate.exchange(
            url, HttpMethod.GET, entity, object : TypeReference<List<UserLoginResponse>>() {}
        )

        return response.body?.first()?.copy(tokenResponse = token) ?: throw RuntimeException("Usuario incorreto!")
    }

    fun create(userCreated: UserCreatedRequest): String {
        val objectMapper = jacksonObjectMapper()
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
            setBearerAuth(token(UserLoginRequest("admin", "admin")).accessToken)
        }

        val entity = HttpEntity(objectMapper.writeValueAsString(userCreated), headers)
        restTemplate.postForEntity(providerProperties.user, entity, String::class.java)
        return "Cadastrado com sucesso, Seja bem vindo: ${userCreated.firstName}!"
    }

    fun token(userLoginRequest: UserLoginRequest): TokenResponse {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_FORM_URLENCODED
        }

        val entity = HttpEntity(userLoginRequest.toBody(), headers)
        val response =
            restTemplate.exchange(providerProperties.token, HttpMethod.POST, entity, TokenResponse::class.java)

        return response.body ?: throw RuntimeException("Usuario incorreto!")
    }
}