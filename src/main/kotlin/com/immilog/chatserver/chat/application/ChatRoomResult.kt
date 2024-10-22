package com.immilog.chatserver.chat.application

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import java.time.LocalDateTime

data class ChatRoomResult(
    val seq: Long?,
    val sender: User?,
    val recipient: User?,
    val lastChat: String?,
    val unreadCountForSender: Int?,
    val unreadCountForRecipient: Int?,
    val lastChatTime: LocalDateTime?
) {
    companion object {
        fun from(chatRoom: ChatRoom): ChatRoomResult {
            return ChatRoomResult(
                seq = chatRoom.seq?.toLong(),
                sender = chatRoom.sender,
                recipient = chatRoom.recipient,
                lastChat = chatRoom.lastChat,
                unreadCountForSender = chatRoom.unreadCountForSender,
                unreadCountForRecipient = chatRoom.unreadCountForRecipient,
                lastChatTime = chatRoom.lastChatTime
            )

        }
    }
}