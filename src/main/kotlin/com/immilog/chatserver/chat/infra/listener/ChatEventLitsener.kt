package com.immilog.chatserver.chat.infra.listener

import com.immilog.chatserver.chat.domain.event.ChatEvent
import com.immilog.chatserver.chat.domain.model.ChatMessage
import com.immilog.chatserver.chat.domain.repository.ChatMessageRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ChatEventListener(private val chatMessageRepository: ChatMessageRepository) {

    @EventListener
    fun handleChatEvent(
        event: ChatEvent
    ) {
        updateDatabase(event)
        notifyOtherServices(event)
    }

    private fun updateDatabase(
        event: ChatEvent
    ) {
        val chatMessage = ChatMessage(
            content = event.message,
            chatRoomSeq = event.chatRoomSeq,
            senderSeq = event.senderSeq,
            recipientSeq = event.recipientSeq,
            attachments = event.attachments,
            createdAt = event.timestamp,
            readStatus = false,
        )
        chatMessageRepository.save(chatMessage)
    }

    private fun notifyOtherServices(
        event: ChatEvent
    ) {
        //TODO: 파이어베이스 알림 서비스 호출
    }
}