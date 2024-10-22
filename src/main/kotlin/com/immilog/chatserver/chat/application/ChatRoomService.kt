package com.immilog.chatserver.chat.application

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.exception.ChatErrorCode
import com.immilog.chatserver.chat.exception.ChatException
import com.immilog.chatserver.chat.infra.gateway.ImmilogGateway
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.util.Pair
import org.springframework.stereotype.Service

@Service
class ChatRoomService(
    private val immilogGateway: ImmilogGateway,
    private val chatRoomRepository: ChatRoomRepository
) {
    fun getChatRoom(
        user: User,
        counterpart: User
    ): ChatRoomResult {
        return chatRoomRepository
            .findChatRoomByUsers(user, counterpart)
            .toResult()
    }

    fun getChatRooms(
        user: User,
        page: Int?
    ): Page<ChatRoomResult> {
        val pageable = PageRequest.of(page ?: 0, 10)
        return chatRoomRepository
            .findChatRoomsByUser(user, pageable)
            .map { result -> result.toResult() }
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

    fun getChatRoomAndRecipient(
        chatRoomSeq: Long?,
        senderSeq: Long
    ): Pair<ChatRoomResult, Long> {
        val chatRoom: ChatRoom = getChatRoom(chatRoomSeq!!)
        val chatRoomResult: ChatRoomResult = ChatRoomResult.from(chatRoom)
        val recipientSeq: Long =
            if (senderSeq == chatRoom.recipient.seq) chatRoom.sender.seq!!
            else chatRoom.recipient.seq!!
        return Pair.of(chatRoomResult, recipientSeq)
    }

    private fun getChatRoom(
        chatRoomSeq: Long
    ): ChatRoom {
        return chatRoomRepository
            .findChatRoomBySeq(chatRoomSeq)
            .orElseThrow { ChatException(ChatErrorCode.CHAT_ROOM_NOT_FOUND) }
    }
}