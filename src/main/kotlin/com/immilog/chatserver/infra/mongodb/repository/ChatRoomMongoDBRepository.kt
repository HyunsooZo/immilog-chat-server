package com.immilog.chatserver.infra.mongodb.repository

import com.immilog.chatserver.infra.mongodb.collections.ChatRoomCollection
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ChatRoomMongoDBRepository : ReactiveMongoRepository<ChatRoomCollection, String> {
}