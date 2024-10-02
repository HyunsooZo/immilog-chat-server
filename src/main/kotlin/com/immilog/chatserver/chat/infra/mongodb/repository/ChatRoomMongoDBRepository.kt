package com.immilog.chatserver.chat.infra.mongodb.repository

import com.immilog.chatserver.chat.infra.mongodb.collections.ChatRoomCollection
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatRoomMongoDBRepository : MongoRepository<ChatRoomCollection, String> {
    fun findBySenderIdAndRecipientId(
        senderSeq: Long,
        recipientSeq: Long
    ): List<ChatRoomCollection>
}