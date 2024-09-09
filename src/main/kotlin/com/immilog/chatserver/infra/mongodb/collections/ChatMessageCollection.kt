package com.immilog.chatserver.infra.mongodb.collections

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "chatMessages")
data class ChatMessageCollection(
    @Id val id: String,
    val chatRoomCollection: ChatRoomCollection? = null,
    val content: String? = null,
    val senderId: String? = null,
    val recipientId: String? = null,
    val readStatus: Boolean = false,
    val invisibleToSender: Boolean? = null,
    val invisibleToRecipient: Boolean? = null,
    val attachments: List<String> = listOf()
)