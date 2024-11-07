package com.kotlin.sqs.adapter.input.controller

import com.kotlin.sqs.domain.core.UserCore
import com.kotlin.sqs.domain.model.UserLoginRequest
import com.kotlin.sqs.domain.model.TokenResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/user")
class UserController(private val userCore: UserCore) {

    @PostMapping("/login")
    fun login(@RequestBody userLoginRequest: UserLoginRequest): TokenResponse {
        return userCore.login(userLoginRequest)
    }

    @GetMapping("/register")
    fun register(): String {
        return "Cadastrado com sucesso!"
    }
}