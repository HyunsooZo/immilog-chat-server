package com.immilog.chatserver.chat.domain.repository

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ChatRoomRepository {
    fun findChatRoomByUsers(
        userSeq: User,
        otherUserSeq: User
    ): ChatRoom

    fun findChatRoomsByUser(
        user: User,
        pageable: Pageable
    ): Page<ChatRoom>
}