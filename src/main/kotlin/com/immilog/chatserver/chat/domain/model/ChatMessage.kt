package com.immilog.chatserver.chat.domain.model

import java.time.LocalDateTime

data class ChatMessage(
    val id: Long? = null,
    val chatRoomSeq: Long? = null,
    val content: String? = null,
    val sender: User? = null,
    val recipient: User? = null,
    val readStatus: Boolean? = null,
    val attachments: List<String>? = null,
    val createdAt: LocalDateTime? = null
)