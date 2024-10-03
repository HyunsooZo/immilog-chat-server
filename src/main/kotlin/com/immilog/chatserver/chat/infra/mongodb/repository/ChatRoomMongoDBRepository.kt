package com.immilog.chatserver.chat.infra.mongodb.repository

import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.infra.mongodb.collections.ChatRoomCollection
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface ChatRoomMongoDBRepository : MongoRepository<ChatRoomCollection, String> {
    fun findBySenderAndRecipientAndIsVisibleToRecipientAndIsVisibleToSender(
        sender: User,
        recipient: User,
        isVisibleToRecipient: Boolean,
        isVisibleToSender: Boolean
    ): ChatRoomCollection

    fun findBySenderOrRecipient(
        sender: User,
        recipient: User,
        pageable: Pageable
    ): Page<ChatRoomCollection>
}