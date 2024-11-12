package com.kotlin.sqs.domain.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class UserCreatedRequest @JsonCreator constructor(
    @JsonProperty("username") val username: String,
    @JsonProperty("enabled") val enabled: Boolean = true,
    @JsonProperty("firstName") val firstName: String,
    @JsonProperty("lastName") val lastName: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("emailVerified") val emailVerified: Boolean = true,
    @JsonProperty("credentials") val credentials: List<CredentialsRequest>
) {
    data class CredentialsRequest(
        @JsonProperty("type") val type: String = "password",
        @JsonProperty("value") val value: String,
        @JsonProperty("temporary") val temporary: Boolean = false
    )
}