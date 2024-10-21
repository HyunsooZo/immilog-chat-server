package com.immilog.chatserver.chat.domain.repository

import com.immilog.chatserver.chat.domain.model.ChatMessage
import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

interface ChatMessageRepository {
    fun save(chatMessage: ChatMessage): Mono<ChatMessage>
}