package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatRoomMongoDBRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

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

}