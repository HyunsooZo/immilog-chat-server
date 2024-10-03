package com.immilog.chatserver.chat.application

import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.infra.gateway.ImmilogGateway
import com.immilog.chatserver.chat.presentation.controller.ChatRoomController
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ChatRoomService(
    private val immilogGateway: ImmilogGateway,
    private val chatRoomRepository: ChatRoomRepository
) {
    fun getChatRoom(
        user: User,
        counterpart: User
    ): ChatRoomController.ChatRoomResult {
        return chatRoomRepository
            .findChatRoomByUsers(user, counterpart)
            .toApiResult()
    }

    fun getChatRooms(
        user: User,
        page: Int?
    ): Page<ChatRoomController.ChatRoomResult> {
        val pageable = PageRequest.of(page ?: 0, 10)
        return chatRoomRepository
            .findChatRoomsByUser(user, pageable)
            .map { result -> result.toApiResult() }
    }

    fun deleteChatRoom(
        chatRoomSeq: Long?,
        userSeq: Long
    ) {
        TODO()
    }

    fun getNewChatRooms(
        userSeq: Long
    ): Int? {
        TODO()
    }

    fun searchChatRooms(
        userSeq: Long,
        keyword: String?
    ): Int? {
        TODO()
    }
}