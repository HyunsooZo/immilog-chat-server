package com.immilog.chatserver.chat.infra.mongodb.collections

import com.immilog.chatserver.chat.domain.model.ChatMessage
import com.immilog.chatserver.chat.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "chatMessage")
data class ChatMessageCollection(
    @Id val id: String? = null,
    @Indexed val chatRoomSeq: Long,
    val content: String,
    val sender: User,
    val recipient: User,
    val readStatus: Boolean,
    val invisibleToSender: Boolean,
    val invisibleToRecipient: Boolean,
    val attachments: List<String> = listOf(),
    @Indexed val createdAt: LocalDateTime
) {
    fun toDomain(): ChatMessage {
        return ChatMessage(
            id = this.id,
            chatRoomSeq = this.chatRoomSeq,
            content = this.content,
            sender = this.sender,
            recipient = this.recipient,
            readStatus = this.readStatus,
            attachments = this.attachments,
            createdAt = this.createdAt
        )
    }
}

