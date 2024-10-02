package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.infra.mongodb.collections.ChatRoomCollection
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatRoomMongoDBRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class ChatRoomRepositoryImpl(
    private val chatRoomMongoDBRepository: ChatRoomMongoDBRepository
) : ChatRoomRepository {
    override fun findChatRoomsByUserSeqId(
        userSeqId: Long,
        otherUserSeqId: Long
    ): List<ChatRoom> {
        return chatRoomMongoDBRepository.findBySenderIdAndRecipientId(
            userSeqId,
            otherUserSeqId
        )
            .map { chatRoomCollection ->
                val lastChat = if (chatRoomCollection.chats.isEmpty()) " "
                else chatRoomCollection.chats.last().content ?: " "
                val senderSeq = chatRoomCollection.senderId
                val recipientSeq = chatRoomCollection.recipientId
                val lastChatTime = if (chatRoomCollection.chats.isEmpty()) LocalDateTime.now()
                else chatRoomCollection.chats.lastOrNull()?.createdAt ?: LocalDateTime.now()

                ChatRoom.Builder()
                    .seq(chatRoomCollection.seq.toLong())
                    .senderId(senderSeq)
                    .recipientId(recipientSeq)
                    .lastChat(lastChat)
                    .lastChatTime(lastChatTime)
                    .unreadCountForSender(unReadChatsForSender(chatRoomCollection))
                    .unreadCountForRecipient(unreadChatsOfRecipient(chatRoomCollection))
                    .build()
            }
    }

    private fun unReadChatsForSender(
        chatRoomCollection: ChatRoomCollection
    ) = chatRoomCollection.chats
            .stream()
            .filter { chat -> Objects.equals(chat.senderId, chatRoomCollection.recipientId) }
            .filter { chat -> !chat.readStatus }
            .count()
            .toInt()

    private fun unreadChatsOfRecipient(
        chatRoomCollection: ChatRoomCollection
    ) = chatRoomCollection.chats.stream()
            .filter { chat -> Objects.equals(chat.senderId, chatRoomCollection.senderId) }
            .filter { chat -> !chat.readStatus }
            .count()
            .toInt()
}