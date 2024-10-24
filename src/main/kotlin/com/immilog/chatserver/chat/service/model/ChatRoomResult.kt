package com.immilog.chatserver.chat.service.model

import com.immilog.chatserver.chat.domain.model.User
import java.time.LocalDateTime

data class ChatRoomResult(
    val seq: Long?,
    val sender: User,
    val recipient: User,
    val lastChat: String?,
    val unreadCountForSender: Int?,
    val unreadCountForRecipient: Int?,
    val lastChatTime: LocalDateTime?
)

data class ChatMessageResult(
    val id: String?,
    val chatRoomSeq: Long,
    val content: String?,
    val sender: User,
    val recipient: User,
    val readStatus: Boolean?,
    val attachments: List<String>?,
    val createdAt: LocalDateTime?
)
