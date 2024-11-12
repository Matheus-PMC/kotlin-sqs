package com.kotlin.sqs.domain.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class UserLoginResponse @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("username") val username: String,
    @JsonProperty("firstName") val firstName: String,
    @JsonProperty("lastName") val lastName: String,
    @JsonProperty("email") val email: String,
    var tokenResponse: TokenResponse? = null,
)
