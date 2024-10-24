package com.immilog.chatserver.chat.domain.model

import com.immilog.chatserver.chat.infra.mongodb.collections.ChatRoomCollection
import com.immilog.chatserver.chat.service.model.ChatRoomResult
import java.time.LocalDateTime
import java.util.*

data class ChatRoom(
    val seq: Long? = null,
    val sender: User,
    val recipient: User,
    val lastChat: String? = null,
    val unreadCountForSender: Int? = null,
    val unreadCountForRecipient: Int? = null,
    val lastChatTime: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) {
    companion object {
        fun from(
            chatRoomCollection: ChatRoomCollection
        ): ChatRoom {
            val lastChat =
                if (chatRoomCollection.chats.isEmpty()) " " else chatRoomCollection.chats.last().content
            val lastChatTime =
                if (chatRoomCollection.chats.isEmpty()) LocalDateTime.now() else chatRoomCollection.chats.last().createdAt
            val unreadCountForSender = chatRoomCollection.chats.stream()
                .filter { chat ->
                    Objects.equals(
                        chat.sender.seq,
                        chatRoomCollection.recipient.seq
                    )
                }
                .filter { chat -> !chat.readStatus }
                .count()
                .toInt()

            val unreadCountForRecipient = chatRoomCollection.chats.stream()
                .filter { chat -> Objects.equals(chat.sender.seq, chatRoomCollection.sender.seq) }
                .filter { chat -> !chat.readStatus }
                .count()
                .toInt()

            return Builder()
                .seq(chatRoomCollection.seq)
                .sender(chatRoomCollection.sender)
                .recipient(chatRoomCollection.recipient)
                .lastChat(lastChat)
                .lastChatTime(lastChatTime)
                .unreadCountForSender(unreadCountForSender)
                .unreadCountForRecipient(unreadCountForRecipient)
                .build()
        }
    }

    class Builder {
        private var seq: Long? = null
        private var sender: User = User()
        private var recipient: User = User()
        private var lastChat: String? = null
        private var unreadCountForSender: Int? = null
        private var unreadCountForRecipient: Int? = null
        private var lastChatTime: LocalDateTime? = null

        fun seq(seq: Long?) = apply { this.seq = seq }
        fun sender(sender: User) = apply { this.sender = sender }
        fun recipient(recipient: User) = apply { this.recipient = recipient }
        fun lastChat(lastChat: String?) = apply { this.lastChat = lastChat }
        fun unreadCountForSender(unreadCountForSender: Int?) =
            apply { this.unreadCountForSender = unreadCountForSender }

        fun unreadCountForRecipient(unreadCountForRecipient: Int?) =
            apply { this.unreadCountForRecipient = unreadCountForRecipient }

        fun lastChatTime(lastChatTime: LocalDateTime?) = apply { this.lastChatTime = lastChatTime }

        fun build() = ChatRoom(
            seq = seq,
            sender = sender,
            recipient = recipient,
            lastChat = lastChat,
            unreadCountForSender = unreadCountForSender,
            unreadCountForRecipient = unreadCountForRecipient,
            lastChatTime = lastChatTime
        )
    }

    fun toResult(): ChatRoomResult {
        return ChatRoomResult(
            seq = this.seq?.toLong(),
            sender = this.sender,
            recipient = this.recipient,
            lastChat = this.lastChat,
            unreadCountForSender = this.unreadCountForSender,
            unreadCountForRecipient = this.unreadCountForRecipient,
            lastChatTime = this.lastChatTime
        )
    }
}