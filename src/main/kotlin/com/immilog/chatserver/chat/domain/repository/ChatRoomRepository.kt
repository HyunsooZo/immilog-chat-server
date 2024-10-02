package com.immilog.chatserver.chat.domain.repository

import com.immilog.chatserver.chat.domain.model.ChatRoom

interface ChatRoomRepository {
    fun findChatRoomsByUserSeqId(
        userSeqId: Long,
        otherUserSeqId: Long
    ): List<ChatRoom>
}