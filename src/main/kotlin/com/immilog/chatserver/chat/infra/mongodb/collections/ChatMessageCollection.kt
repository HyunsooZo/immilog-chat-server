package com.immilog.chatserver.chat.infra.mongodb.collections

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