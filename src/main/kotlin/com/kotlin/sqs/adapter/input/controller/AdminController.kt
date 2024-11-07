package com.kotlin.sqs.adapter.input.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/admin")
class AdminController {

    @GetMapping("/send")
    fun hello2(): String {
        return "mensagens enviadas!"
    }

    @GetMapping("/receive")
    fun login(): String {
        return "mensagens recebidas!"
    }
}