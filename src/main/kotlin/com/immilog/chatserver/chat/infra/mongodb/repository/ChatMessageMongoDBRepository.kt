package com.immilog.chatserver.chat.infra.mongodb.repository

import com.immilog.chatserver.chat.infra.mongodb.collections.ChatMessageCollection
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatMessageMongoDBRepository : ReactiveMongoRepository<ChatMessageCollection, String> {
}