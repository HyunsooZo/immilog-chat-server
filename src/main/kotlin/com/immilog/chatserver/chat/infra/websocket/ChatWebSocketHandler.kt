package com.immilog.chatserver.chat.api.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.immilog.chatserver.chat.domain.model.ChatMessage
import com.immilog.chatserver.chat.service.ChatMessageService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class ChatWebSocketHandler(
    private val chatMessageService: ChatMessageService,
    private val objectMapper: ObjectMapper
) : WebSocketHandler {

    override fun handle(
        session: WebSocketSession
    ): Mono<Void> {
        val receive = session.receive()
            .map { it.payloadAsText }
            .flatMap { payload ->
                try {
                    val message = objectMapper.readValue(
                        payload,
                        ChatMessage::class.java
                    )
                    chatMessageService.saveMessage(message)
                        .flatMap { savedMessage ->
                            val response = objectMapper.writeValueAsString(savedMessage)
                            session.send(Mono.just(session.textMessage(response)))
                        }
                } catch (e: Exception) {
                    session.send(Mono.just(session.textMessage("Invalid message format")))
                }
            }

        val send = chatMessageService.getMessageStream()
            .map { message ->
                objectMapper.writeValueAsString(message)
            }
            .map { session.textMessage(it) }

        return session.send(send).and(receive)
    }
}
