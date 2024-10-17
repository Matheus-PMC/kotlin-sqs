package com.kotlin.sqs

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class SendMessageController(
    private val amazonSQS: AmazonSQS,
    private val objectMapper: ObjectMapper
) {

    @GetMapping("/sendMessage")
    fun sendMessage(): ResponseEntity<*> {
        val sendMessageRequest = SendMessageRequest()
            .withQueueUrl("sample-queue")
            .withMessageBody(objectMapper.writeValueAsString("test"))
        amazonSQS.sendMessage(sendMessageRequest);
        return ResponseEntity.ok().build<Any>()
    }
}