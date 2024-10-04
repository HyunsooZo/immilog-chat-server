package com.immilog.chatserver.chat.domain.model

import com.immilog.chatserver.chat.domain.event.ChatEvent
import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import java.time.LocalDateTime

data class ChatMessage(
    val id: String? = null,
    val chatRoomSeq: String,
    val content: String? = null,
    val senderSeq: Long? = null,
    val recipientSeq: Long? = null,
    val readStatus: Boolean? = null,
    val attachments: List<String>? = null,
    val createdAt: LocalDateTime? = null
) {
    fun from(
        chatEvent: ChatEvent
    ): ChatMessage {
        return ChatMessage(
            chatRoomSeq = chatEvent.chatRoomSeq,
            content = chatEvent.message,
            senderSeq = chatEvent.senderSeq,
            recipientSeq = chatEvent.recipientSeq,
            readStatus = false,
            attachments = chatEvent.attachments,
            createdAt = chatEvent.timestamp
        )
    }

    fun toCollection(): ChatMessageCollection = ChatMessageCollection(
        id = id,
        chatRoomSeq = chatRoomSeq,
        content = content,
        senderSeq = senderSeq,
        recipientSeq = recipientSeq,
        readStatus = readStatus ?: false,
        invisibleToSender = false,
        invisibleToRecipient = false,
        attachments = attachments ?: listOf(),
        createdAt = createdAt ?: LocalDateTime.now()
    )
}