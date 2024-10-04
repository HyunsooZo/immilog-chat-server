package com.immilog.chatserver.chat.infra.mongodb.repository

import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.infra.mongodb.collections.ChatRoomCollection
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ChatRoomMongoDBRepository : ReactiveMongoRepository<ChatRoomCollection, String> {
    fun findBySenderAndRecipientAndIsVisibleToRecipientAndIsVisibleToSender(
        sender: User,
        recipient: User,
        isVisibleToRecipient: Boolean,
        isVisibleToSender: Boolean
    ): Mono<ChatRoomCollection>

    fun findBySenderOrRecipient(
        sender: User,
        recipient: User,
        pageable: Pageable
    ): Flux<ChatRoomCollection>
}