package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.repository.ChatMessageRepository
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatMessageMongoDBRepository
import org.springframework.stereotype.Repository

@Repository
class ChatMessageRepositoryImpl(
    private val chatMessageMongoDBRepository: ChatMessageMongoDBRepository
) : ChatMessageRepository {
}