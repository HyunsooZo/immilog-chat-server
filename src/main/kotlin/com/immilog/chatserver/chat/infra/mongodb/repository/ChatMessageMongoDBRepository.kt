package com.immilog.chatserver.chat.infra.mongodb.repository

import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageMongoDBRepository : ReactiveMongoRepository<ChatMessageCollection, String> {
}