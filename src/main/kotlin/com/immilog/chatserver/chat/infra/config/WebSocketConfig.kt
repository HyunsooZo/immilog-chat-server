package com.immilog.chatserver.chat.infra.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

@Configuration
class WebSocketConfig(
    private val chatWebSocketHandler: WebSocketHandler
) {
    @Bean
    fun webSocketHandlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun webSocketHandlerMapping(): SimpleUrlHandlerMapping {
        val map = mapOf("/ws/chat" to chatWebSocketHandler)
        return SimpleUrlHandlerMapping().apply {
            urlMap = map
        }
    }
}
