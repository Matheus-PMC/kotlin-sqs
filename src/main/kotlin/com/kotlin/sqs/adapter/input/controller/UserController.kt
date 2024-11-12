package com.kotlin.sqs.adapter.input.controller

import com.kotlin.sqs.domain.core.UserCore
import com.kotlin.sqs.domain.model.UserCreatedRequest
import com.kotlin.sqs.domain.model.UserLoginRequest
import com.kotlin.sqs.domain.model.UserLoginResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userCore: UserCore) {

    @PostMapping("/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest): UserLoginResponse {
        return userCore.login(userLoginRequest)
    }

    @PostMapping("/register")
    fun register(@RequestBody userCreated: UserCreatedRequest): String {
        return userCore.create(userCreated)
    }
}