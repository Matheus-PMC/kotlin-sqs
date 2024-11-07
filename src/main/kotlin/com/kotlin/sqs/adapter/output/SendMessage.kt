package com.kotlin.sqs.adapter.output

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping


@Service
class SendMessage(
    private val amazonSQS: AmazonSQS,
    private val objectMapper: ObjectMapper
) {

    @GetMapping("/sendMessage")
    fun sendMessage(): ResponseEntity<*> {
        val sendMessageRequest = SendMessageRequest()
            .withQueueUrl("sample-queue")
            .withMessageBody(objectMapper.writeValueAsString("test3"))
        amazonSQS.sendMessage(sendMessageRequest);
        return ResponseEntity.ok().build<Any>()
    }
}