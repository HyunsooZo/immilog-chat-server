package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.exception.ChatErrorCode
import com.immilog.chatserver.chat.exception.ChatException
import com.immilog.chatserver.chat.infra.mongodb.collections.toCollection
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatRoomMongoDBRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class ChatRoomRepositoryImpl(
    private val chatRoomMongoDBRepository: ChatRoomMongoDBRepository
) : ChatRoomRepository {

    override fun findChatRoomByUsers(
        userSeq: User,
        otherUserSeq: User
    ): Mono<ChatRoom> {
        return chatRoomMongoDBRepository.findBySenderAndRecipientAndIsVisibleToRecipientAndIsVisibleToSender(
            userSeq,
            otherUserSeq,
            isVisibleToRecipient = true,
            isVisibleToSender = true
        )
            .map { chatRoomCollection ->
                ChatRoom.from(chatRoomCollection)
            }
            .switchIfEmpty(Mono.error(ChatException(ChatErrorCode.CHAT_ROOM_NOT_FOUND)))
    }

    override fun findChatRoomsByUser(
        user: User,
        pageable: Pageable
    ): Mono<Page<ChatRoom>> {
        return chatRoomMongoDBRepository.findBySenderOrRecipient(user, user)
            .map { chatRoomCollection -> ChatRoom.from(chatRoomCollection) }
            .collectList()
            .flatMap { chatRoomList ->
                chatRoomMongoDBRepository.countBySenderOrRecipient(user, user)
                    .map { count ->
                        PageImpl(chatRoomList, pageable, count)
                    }
            }
    }

    override fun findChatRoomBySeq(
        chatRoomSeq: Long
    ): Mono<ChatRoom> {
        return chatRoomMongoDBRepository.findById(chatRoomSeq.toString())
            .map { chatRoomCollection -> ChatRoom.from(chatRoomCollection) }
            .switchIfEmpty(Mono.error(ChatException(ChatErrorCode.CHAT_ROOM_NOT_FOUND)))
    }

    override fun save(toCollection: ChatRoom): Mono<ChatRoom> {
        return chatRoomMongoDBRepository
            .save(toCollection.toCollection())
            .map { it.toDomain() }
    }
}
