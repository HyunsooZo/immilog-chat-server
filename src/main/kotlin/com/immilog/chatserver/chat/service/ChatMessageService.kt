package com.immilog.chatserver.chat.service

import com.immilog.chatserver.chat.domain.model.ChatMessage
import com.immilog.chatserver.chat.domain.repository.ChatMessageRepository
import com.immilog.chatserver.chat.service.model.ChatMessageResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

@Service
class ChatMessageService(
    private val chatMessageRepository: ChatMessageRepository
) {
    private val sink: Sinks.Many<ChatMessageResult> =
        Sinks.many().multicast().onBackpressureBuffer()

    fun getChatMessages(
        chatRoomSeq: Long,
        pageable: Pageable
    ): Mono<Page<ChatMessageResult>> {
        val messagesMono = chatMessageRepository
            .findChatsByRoom(chatRoomSeq, pageable)
            .map { it.toDomain().toResult() }
            .collectList()

        val countMono = chatMessageRepository.countByChatRoomSeq(chatRoomSeq)

        return Mono.zip(messagesMono, countMono)
            .map { tuple ->
                val messages = tuple.t1
                val count = tuple.t2
                PageImpl(messages, pageable, count)
            }
    }

    fun saveMessage(message: ChatMessage): Mono<ChatMessageResult> {
        return chatMessageRepository
            .save(message.toCollection())
            .map { it.toDomain().toResult() }
    }

    fun getMessageStream(): Flux<ChatMessageResult> = sink.asFlux()

}
