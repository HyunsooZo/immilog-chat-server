package com.immilog.chatserver.chat.infra.mongodb.collections

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "chatRoom")
data class ChatRoomCollection(
    @Id val id: String? = null,
    @Indexed val seq: Long,
    val sender: User,
    val recipient: User,
    val chats: List<ChatMessageCollection> = listOf(),
    val isVisibleToSender: Boolean,
    val isVisibleToRecipient: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    fun toDomain(): ChatRoom {
        return ChatRoom.from(this)
    }
}

fun ChatRoom.toCollection(): ChatRoomCollection {
    return ChatRoomCollection(
        seq = this.seq ?: System.currentTimeMillis(),
        sender = this.sender,
        recipient = this.recipient,
        chats = listOf(),
        isVisibleToRecipient = true,
        isVisibleToSender = true,
        createdAt = this.createdAt ?: LocalDateTime.now(),
        updatedAt = this.updatedAt ?: LocalDateTime.now()
    )
}
