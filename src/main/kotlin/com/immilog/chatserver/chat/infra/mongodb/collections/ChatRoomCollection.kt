package com.immilog.chatserver.chat.infra.mongodb.collections

import com.immilog.chatserver.chat.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "chatRoom")
data class ChatRoomCollection(
    @Id val seq: String,
    val sender: User,
    val recipient: User,
    var isVisibleToSender: Boolean? = null,
    var isVisibleToRecipient: Boolean? = null,
    @DBRef val chats: List<ChatMessageCollection> = mutableListOf(),
    val createdAt: LocalDateTime? = null
)