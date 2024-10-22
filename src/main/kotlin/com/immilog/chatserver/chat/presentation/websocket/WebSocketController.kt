package com.immilog.chatserver.chat.presentation.websocket

import com.immilog.chatserver.chat.application.ChatMessageService
import com.immilog.chatserver.chat.application.ChatRoomResult
import com.immilog.chatserver.chat.application.ChatRoomService
import com.immilog.chatserver.chat.domain.model.User
import lombok.RequiredArgsConstructor
import org.springframework.data.util.Pair
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import java.time.LocalDateTime

@RequiredArgsConstructor
@Controller
class WebSocketController(
    private val chatMessageService: ChatMessageService,
    private val chatRoomService: ChatRoomService,
    private val messagingTemplate: SimpMessagingTemplate
) {

    @MessageMapping("/chat/send")
    fun sendMessage(
        @Payload chatMessageRequest: ChatMessageRequest
    ): ChatMessageResponse {
        val chatMessageResult: ChatMessageService.ChatMessageResult =
            chatMessageService.saveChat(chatMessageRequest)
        val chatMessageResponse = ChatMessageResponse(
            id = chatMessageResult.id!!.toLong(),
            chatRoomSeq = chatMessageResult.chatRoomSeq.toLong(),
            content = chatMessageResult.content!!,
            sender = chatMessageResult.sender!!,
            recipient = chatMessageResult.recipient!!,
            readStatus = false,
            attachments = chatMessageResult.attachments,
            createdAt = chatMessageResult.createdAt!!
        )
        messagingTemplate.convertAndSend(
            "/topic/room/" + chatMessageRequest.chatRoomSeq,
            chatMessageResponse
        )
        val result: Pair<ChatRoomResult, Long> = chatRoomService.getChatRoomAndRecipient(
            chatMessageRequest.chatRoomSeq,
            chatMessageRequest.senderSeq
        )

        messagingTemplate.convertAndSend(
            "/topic/updateChatRoomList/" + result.second,
            result.first
        )
        return chatMessageResponse
    }

    data class ChatMessageRequest(
        val chatRoomSeq: Long,
        val content: String,
        val senderSeq: Long,
        val attachments: List<String>?
    )

    data class ChatMessageResponse(
        val id: Long,
        val chatRoomSeq: Long,
        val content: String,
        val sender: User,
        val recipient: User,
        val readStatus: Boolean,
        val attachments: List<String>?,
        val createdAt: LocalDateTime
    )
}