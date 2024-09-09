package com.immilog.chatserver.infra.mongodb.repository

import com.immilog.chatserver.infra.mongodb.collections.ChatMessageCollection
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatMessageMongoDBRepository : ReactiveMongoRepository<ChatMessageCollection, String> {
}