package com.immilog.chatserver.infra.mongodb.collections

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "chatRooms")
data class ChatRoomCollection(
    @Id val seq: String,
    val sender: Long? = null,
    val recipient: Long? = null,
    var isVisibleToSender: Boolean? = null,
    var isVisibleToRecipient: Boolean? = null,
    @DBRef val chats: List<ChatMessageCollection> = mutableListOf()
)