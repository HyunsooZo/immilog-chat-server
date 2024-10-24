package com.immilog.chatserver.chat.domain.model

import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import com.immilog.chatserver.chat.service.model.ChatMessageResult
import java.time.LocalDateTime

data class ChatMessage(
    val id: String? = null,
    val chatRoomSeq: Long,
    val content: String? = null,
    val sender: User,
    val recipient: User,
    val readStatus: Boolean? = null,
    val attachments: List<String>? = null,
    val createdAt: LocalDateTime? = null
) {
    fun toCollection(): ChatMessageCollection = ChatMessageCollection(
        id = id,
        chatRoomSeq = chatRoomSeq,
        content = content ?: "",
        sender = sender,
        recipient = recipient,
        readStatus = readStatus ?: false,
        invisibleToSender = false,
        invisibleToRecipient = false,
        attachments = attachments ?: listOf(),
        createdAt = createdAt ?: LocalDateTime.now()
    )

    fun toResult(): ChatMessageResult = ChatMessageResult(
        id = id,
        chatRoomSeq = chatRoomSeq,
        content = content,
        sender = sender,
        recipient = recipient,
        readStatus = readStatus,
        attachments = attachments,
        createdAt = createdAt
    )
}
