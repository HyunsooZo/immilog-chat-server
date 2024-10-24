package com.immilog.chatserver.chat.infra.mongodb.repository

import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.infra.mongodb.collections.ChatRoomCollection
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ChatRoomMongoDBRepository : ReactiveMongoRepository<ChatRoomCollection, String> {
    fun findBySenderOrRecipient(
        sender: User,
        recipient: User,
    ): Flux<ChatRoomCollection>

    fun findBySeq(
        seq: Long
    ): Mono<ChatRoomCollection>

    fun findBySenderAndRecipientAndIsVisibleToRecipientAndIsVisibleToSender(
        userSeq: User,
        otherUserSeq: User,
        isVisibleToRecipient: Boolean,
        isVisibleToSender: Boolean
    ): Mono<ChatRoomCollection>

    fun countBySenderOrRecipient(
        user: User,
        user1: User
    ): Mono<Long>
}
