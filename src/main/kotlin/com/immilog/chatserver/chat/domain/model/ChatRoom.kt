package com.immilog.chatserver.chat.domain.model

import com.immilog.chatserver.chat.infra.mongodb.collections.ChatRoomCollection
import java.time.LocalDateTime
import java.util.*

data class ChatRoom(
    val seq: Long? = null,
    val senderId: Long? = null,
    val recipientId: Long? = null,
    val lastChat: String? = null,
    val unreadCountForSender: Int? = null,
    val unreadCountForRecipient: Int? = null,
    val lastChatTime: LocalDateTime? = null
) {
    companion object {
        fun from(chatRoomCollection: ChatRoomCollection): ChatRoom {
            return Builder()
                .seq(chatRoomCollection.seq.toLong())
                .senderId(chatRoomCollection.senderId)
                .recipientId(chatRoomCollection.recipientId)
                .lastChat(
                    if (chatRoomCollection.chats.isEmpty()) " "
                    else chatRoomCollection.chats.last().content
                )
                .lastChatTime(
                    if (chatRoomCollection.chats.isEmpty()) LocalDateTime.now()
                    else chatRoomCollection.chats.last().createdAt
                )
                .unreadCountForSender(
                    chatRoomCollection.chats.stream()
                        .filter { chat -> Objects.equals(chat.senderId, chatRoomCollection.recipientId) }
                        .filter { chat -> !chat.readStatus }
                        .count()
                        .toInt()
                )
                .unreadCountForRecipient(
                    chatRoomCollection.chats.stream()
                        .filter { chat -> Objects.equals(chat.senderId, chatRoomCollection.senderId) }
                        .filter { chat -> !chat.readStatus }
                        .count()
                        .toInt()
                )
                .build()
        }
    }

    class Builder {
        private var seq: Long? = null
        private var senderId: Long? = null
        private var recipientId: Long? = null
        private var lastChat: String? = null
        private var unreadCountForSender: Int? = null
        private var unreadCountForRecipient: Int? = null
        private var lastChatTime: LocalDateTime? = null

        fun seq(seq: Long) = apply { this.seq = seq }
        fun senderId(senderId: Long?) = apply { this.senderId = senderId }
        fun recipientId(recipientId: Long?) = apply { this.recipientId = recipientId }
        fun lastChat(lastChat: String?) = apply { this.lastChat = lastChat }
        fun unreadCountForSender(unreadCountForSender: Int?) = apply { this.unreadCountForSender = unreadCountForSender }
        fun unreadCountForRecipient(unreadCountForRecipient: Int?) = apply { this.unreadCountForRecipient = unreadCountForRecipient }
        fun lastChatTime(lastChatTime: LocalDateTime?) = apply { this.lastChatTime = lastChatTime }

        fun build() = ChatRoom(
            seq = seq,
            senderId = senderId,
            recipientId = recipientId,
            lastChat = lastChat,
            unreadCountForSender = unreadCountForSender,
            unreadCountForRecipient = unreadCountForRecipient,
            lastChatTime = lastChatTime
        )
    }
}