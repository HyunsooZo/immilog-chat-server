package com.immilog.chatserver.infra.persistence

import com.immilog.chatserver.domain.repository.ChatRoomRepository
import com.immilog.chatserver.infra.mongodb.repository.ChatRoomMongoDBRepository
import org.springframework.stereotype.Repository

@Repository
class ChatRoomRepositoryImpl(
    private val chatRoomMongoDBRepository: ChatRoomMongoDBRepository
) : ChatRoomRepository {
}