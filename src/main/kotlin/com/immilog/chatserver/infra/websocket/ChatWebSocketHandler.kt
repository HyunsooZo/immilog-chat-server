package com.immilog.chatserver.infra.websocket

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class ChatWebSocketHandler : WebSocketHandler {
    override fun handle(
        session: WebSocketSession
    ): Mono<Void> {
        return session.send(
            session.receive()
                .map { message -> message.payloadAsText }
                .map { session.textMessage(it) }
        )
    }
}