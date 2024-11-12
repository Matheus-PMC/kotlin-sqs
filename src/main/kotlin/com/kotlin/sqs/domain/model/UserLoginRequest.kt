package com.kotlin.sqs.domain.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class UserLoginRequest @JsonCreator constructor(
    @JsonProperty("username") val username: String,
    @JsonProperty("password") val password: String
) {
    fun toBody(): String {
        return "username=${username}&password=${password}&client_id=admin&grant_type=password&scope=openid"
    }
}
