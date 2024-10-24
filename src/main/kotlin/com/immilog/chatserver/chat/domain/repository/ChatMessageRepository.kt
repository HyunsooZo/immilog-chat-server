package com.immilog.chatserver.chat.domain.repository

import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ChatMessageRepository {
    fun findChatsByRoom(
        chatRoomSeq: Long,
        pageable: Pageable
    ): Flux<ChatMessageCollection>

    fun countByChatRoomSeq(
        chatRoomSeq: Long
    ): Mono<Long>

    fun save(
        chatMessageCollection: ChatMessageCollection
    ): Mono<ChatMessageCollection>
}
