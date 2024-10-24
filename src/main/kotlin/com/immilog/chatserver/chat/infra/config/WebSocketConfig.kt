package com.immilog.chatserver.chat.config

import com.immilog.chatserver.chat.api.websocket.ChatWebSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

@Configuration
class WebSocketConfig {

    @Bean
    fun handlerMapping(
        chatWebSocketHandler: ChatWebSocketHandler
    ): HandlerMapping {
        val map: MutableMap<String, Any> = LinkedHashMap()
        map["/ws/chat"] = chatWebSocketHandler
        val mapping = SimpleUrlHandlerMapping()
        mapping.order = -1
        mapping.urlMap = map
        return mapping
    }

    @Bean
    fun handlerAdapter() = WebSocketHandlerAdapter()
}
