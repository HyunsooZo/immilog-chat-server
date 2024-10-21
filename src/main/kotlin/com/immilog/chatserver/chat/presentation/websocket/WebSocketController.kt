package com.immilog.chatserver.chat.presentation.websocket

import com.immilog.chatserver.chat.application.ChatRoomService
import com.immilog.chatserver.chat.application.ChatMessageService
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.presentation.controller.ChatRoomController
import lombok.RequiredArgsConstructor
import org.springframework.data.util.Pair
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import java.time.LocalDateTime


@RequiredArgsConstructor
@Controller
class WebSocketController {
    private val chatMessageService: ChatMessageService? = null
    private val chatRoomService: ChatRoomService? = null
    private val messagingTemplate: SimpMessagingTemplate? = null

    @MessageMapping("/chat/send")
    fun sendMessage(
        @Payload chatMessageRequest: ChatMessageRequest
    ): ChatMessageResponse {
        val chatResult: ChatMessageService.ChatMessageResult = chatMessageService?.saveChat(chatMessageRequest) ?:
        messagingTemplate.convertAndSend(
            "/topic/room/" + chatMessageRequest.getChatRoomSeq(), chatDto
         )
        val result: Pair<ChatRoomResult, Long> = chatRoomService.getChatRoomAndRecipient(
            chatMessageRequest.getChatRoomSeq(), chatMessageRequest.getSenderSeq()
        )

        messagingTemplate.convertAndSend(
            "/topic/updateChatRoomList/" + result.getSecond(),
            result.getFirst()
        )
        return chatDto
    }
    public class ChatMessageRequest {
        val chatRoomSeq: Long? = null
        val content: String? = null
        val senderSeq: Long? = null
        val attachments: List<String>? = null
    }

    class ReadChat {
        private val chatSeq: Long? = null
        private val userSeq: Long? = null
    }

    public class ChatMessageResponse {
        private val id: Long? = null
        private val chatRoomSeq: Long? = null
        private val content: String? = null
        private val sender: User? = null
        private val recipient: User? = null
        private val readStatus: Boolean? = null
        private val attachments: List<String>? = null
        private val createdAt: LocalDateTime? = null
    }
}
