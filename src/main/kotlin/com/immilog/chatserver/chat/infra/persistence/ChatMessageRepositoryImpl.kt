package com.immilog.chatserver.chat.infra.persistence

import com.immilog.chatserver.chat.domain.repository.ChatMessageRepository
import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import com.immilog.chatserver.chat.infra.mongodb.repository.ChatMessageMongoDBRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class ChatMessageRepositoryImpl(
    private val chatMessageMongoDBRepository: ChatMessageMongoDBRepository
) : ChatMessageRepository {

    override fun findChatsByRoom(
        chatRoomSeq: Long,
        pageable: Pageable
    ): Flux<ChatMessageCollection> {
        return chatMessageMongoDBRepository.findByChatRoomSeq(chatRoomSeq, pageable)
    }

    override fun countByChatRoomSeq(
        chatRoomSeq: Long
    ): Mono<Long> {
        return chatMessageMongoDBRepository.countByChatRoomSeq(chatRoomSeq)
    }

    override fun save(
        chatMessageCollection: ChatMessageCollection
    ): Mono<ChatMessageCollection> {
        return chatMessageMongoDBRepository.save(chatMessageCollection)
    }
}
