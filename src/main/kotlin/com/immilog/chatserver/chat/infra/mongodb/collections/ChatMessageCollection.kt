package com.immilog.chatserver.chat.infra.mongodb.collections

import com.immilog.chatserver.chat.domain.model.ChatMessage
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "chatMessage")
data class ChatMessageCollection(
    @Id val id: String? = null,
    val chatRoomSeq: String? = null,
    val content: String? = null,
    val senderSeq: Long? = null,
    val recipientSeq: Long? = null,
    val readStatus: Boolean = false,
    val invisibleToSender: Boolean? = null,
    val invisibleToRecipient: Boolean? = null,
    val attachments: List<String> = listOf(),
    val createdAt: LocalDateTime? = null
)

fun ChatMessageCollection.toDomain(): ChatMessage {
    return ChatMessage(
        id = this.id,
        chatRoomSeq = this.chatRoomSeq.toString(),
        content = this.content,
        senderSeq = this.senderSeq,
        recipientSeq = this.recipientSeq,
        readStatus = this.readStatus,
        attachments = this.attachments,
        createdAt = this.createdAt
    )
}