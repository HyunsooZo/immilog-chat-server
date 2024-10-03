package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatRoomMongoDBRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ChatRoomRepositoryImpl(
    private val chatRoomMongoDBRepository: ChatRoomMongoDBRepository
) : ChatRoomRepository {
    override fun findChatRoomByUsers(
        userSeq: User,
        otherUserSeq: User
    ): ChatRoom {
        return ChatRoom.from(
            chatRoomMongoDBRepository.findBySenderAndRecipientAndIsVisibleToRecipientAndIsVisibleToSender(
                userSeq,
                otherUserSeq,
                isVisibleToRecipient = true,
                isVisibleToSender = true
            )
        )
    }

    override fun findChatRoomsByUser(
        user: User,
        pageable: Pageable
    ): Page<ChatRoom> {
        return chatRoomMongoDBRepository
            .findBySenderOrRecipient(user, user, pageable)
            .map { chatRoomCollection -> ChatRoom.from(chatRoomCollection) }
    }

}