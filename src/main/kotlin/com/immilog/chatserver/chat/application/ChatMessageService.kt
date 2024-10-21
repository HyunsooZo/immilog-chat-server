package com.immilog.chatserver.chat.application

import com.immilog.chatserver.chat.domain.model.ChatMessage
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatMessageRepository
import com.immilog.chatserver.chat.presentation.websocket.WebSocketController
import org.springframework.stereotype.Service
import reactor.kotlin.core.publisher.toMono
import java.time.LocalDateTime

@Service
class ChatMessageService(
    private val chatMessageRepository: ChatMessageRepository
) {
    fun saveChat(
        chatMessageRequest: WebSocketController.ChatMessageRequest
    ): ChatMessageResult {
        val chatMessage = ChatMessage.from(chatMessageRequest)
        val chatMessageMono = chatMessageRepository.save(chatMessage).toMono()
        return ChatMessageResult.from(chatMessageMono.block())
    }

    class ChatMessageResult(
        val id: String?,
        val chatRoomSeq: String,
        val content: String?,
        val sender: User?,
        val recipient: User?,
        val readStatus: Boolean?,
        val attachments: List<String>?,
        val createdAt: LocalDateTime?
    ) {
        companion object {
            fun from(chatMessage: ChatMessage?): ChatMessageResult {
                return ChatMessageResult(
                    id = chatMessage?.id,
                    chatRoomSeq = chatMessage?.chatRoomSeq.toString(),
                    content = chatMessage?.content,
                    sender = chatMessage?.senderSeq?.let { User(it) },
                    recipient = chatMessage?.recipientSeq?.let { User(it) },
                    readStatus = chatMessage?.readStatus,
                    attachments = chatMessage?.attachments,
                    createdAt = chatMessage?.createdAt
                )
            }
        }
    }
}