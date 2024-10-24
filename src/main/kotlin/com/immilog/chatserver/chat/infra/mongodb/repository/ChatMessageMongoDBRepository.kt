package com.immilog.chatserver.chat.infra.mongodb.repository

import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ChatMessageMongoDBRepository : ReactiveMongoRepository<ChatMessageCollection, String> {
    fun findByChatRoomSeq(
        chatRoomSeq: Long, pageable: Pageable
    ): Flux<ChatMessageCollection>

    fun countByChatRoomSeq(
        chatRoomSeq: Long
    ): Mono<Long>
}
