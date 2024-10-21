package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.model.ChatMessage
import com.immilog.chatserver.chat.domain.repository.ChatMessageRepository
import com.immilog.chatserver.chat.infra.mongodb.collections.toDomain
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatMessageMongoDBRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class ChatMessageRepositoryImpl(
    private val chatMessageMongoDBRepository: ChatMessageMongoDBRepository
) : ChatMessageRepository {
    override fun save(
        chatMessage: ChatMessage
    ): Mono<ChatMessage> {
        return chatMessageMongoDBRepository
            .save(chatMessage.toCollection())
            .map { savedCollection -> savedCollection.toDomain() }
    }
}