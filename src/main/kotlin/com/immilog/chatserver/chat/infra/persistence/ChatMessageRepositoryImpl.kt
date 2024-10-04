package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.model.ChatMessage
import com.immilog.chatserver.chat.domain.repository.ChatMessageRepository
import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatMessageMongoDBRepository
import org.reactivestreams.Publisher
import org.springframework.stereotype.Repository

@Repository
class ChatMessageRepositoryImpl(
    private val chatMessageMongoDBRepository: ChatMessageMongoDBRepository
) : ChatMessageRepository {
    override fun save(chatMessage: ChatMessage): Publisher<ChatMessageCollection> {
        return chatMessageMongoDBRepository.save(chatMessage.toCollection())
    }
}