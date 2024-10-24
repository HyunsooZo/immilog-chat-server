package com.immilog.chatserver.chat.domain.repository

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Mono

interface ChatRoomRepository {
    fun findChatRoomByUsers(
        userSeq: User,
        otherUserSeq: User
    ): Mono<ChatRoom>

    fun findChatRoomsByUser(
        user: User,
        pageable: Pageable
    ): Mono<Page<ChatRoom>>

    fun findChatRoomBySeq(
        chatRoomSeq: Long
    ): Mono<ChatRoom>

    fun save(
        toCollection: ChatRoom
    ): Mono<ChatRoom>
}