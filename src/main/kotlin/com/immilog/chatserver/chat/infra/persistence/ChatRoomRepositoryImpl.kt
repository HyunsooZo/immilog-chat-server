package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatRoomMongoDBRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ChatRoomRepositoryImpl(
    private val chatRoomMongoDBRepository: ChatRoomMongoDBRepository
) : ChatRoomRepository {

    override fun findChatRoomByUsers(
        userSeq: User,
        otherUserSeq: User
    ): ChatRoom {
        return chatRoomMongoDBRepository.findBySenderAndRecipientAndIsVisibleToRecipientAndIsVisibleToSender(
            userSeq,
            otherUserSeq,
            isVisibleToRecipient = true,
            isVisibleToSender = true
        )
            .map { chatRoomCollection ->
                ChatRoom.from(chatRoomCollection)
            }
            .block() ?: throw NoSuchElementException("Chat room not found")
    }

    override fun findChatRoomsByUser(
        user: User,
        pageable: Pageable
    ): Page<ChatRoom> {
        val chatRoomList = chatRoomMongoDBRepository
            .findBySenderOrRecipient(user, user, pageable)
            .map { chatRoomCollection -> ChatRoom.from(chatRoomCollection) }
            .collectList()
            .block() ?: emptyList()

        return PageImpl(
            chatRoomList,
            pageable,
            chatRoomList.size.toLong()
        )
    }

    override fun findChatRoomBySeq(
        chatRoomSeq: Long
    ): Optional<ChatRoom> {
        return chatRoomMongoDBRepository.findById(chatRoomSeq.toString())
            .map { chatRoomCollection -> ChatRoom.from(chatRoomCollection) }
            .blockOptional()
    }

}