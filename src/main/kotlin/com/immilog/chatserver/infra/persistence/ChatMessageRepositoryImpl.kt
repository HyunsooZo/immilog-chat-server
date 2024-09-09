package com.immilog.chatserver.infra.persistence

import com.immilog.chatserver.domain.repository.ChatMessageRepository
import com.immilog.chatserver.infra.mongodb.repository.ChatMessageMongoDBRepository
import org.springframework.stereotype.Repository

@Repository
class ChatMessageRepositoryImpl(
    private val chatMessageMongoDBRepository: ChatMessageMongoDBRepository
) : ChatMessageRepository {
}