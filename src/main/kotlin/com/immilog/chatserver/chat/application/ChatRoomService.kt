package com.immilog.chatserver.chat.application

import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.infra.gateway.ImmilogGateway
import com.immilog.chatserver.chat.presentation.controller.ChatRoomController
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ChatRoomService(
    private val immilogGateway: ImmilogGateway,
    private val chatRoomRepository: ChatRoomRepository
) {
    fun getChatRoom(
        userSeq: Long,
        counterpartSeq: Long
    ): Long {
        chatRoomRepository.findChatRoomsByUserSeqId(userSeq, counterpartSeq)
        return 0L
    }

    fun getChatRooms(
        userSeq: Long,
        page: Int?
    ): Page<ChatRoomController.ChatRoomResult> {
        TODO()
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