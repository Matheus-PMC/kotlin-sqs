package com.kotlin.sqs.adapter.input.controller

import com.kotlin.sqs.adapter.output.SendMessage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/message")
class MessageController(
    private val sendMessage: SendMessage
) {

    @GetMapping("/send")
    fun hello2(): String {
        sendMessage.sendMessage()

        return "mensagens enviadas!"
    }

    @GetMapping("/receive")
    fun login(): String {
        return "mensagens recebidas!"
    }
}