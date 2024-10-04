package com.immilog.chatserver.chat.domain.event

import java.time.LocalDateTime

class ChatEvent(
    val message: String,
    val chatRoomSeq: String,
    val senderSeq: Long,
    val recipientSeq: Long,
    val attachments: List<String>,
    val timestamp: LocalDateTime = LocalDateTime.now()
){
}
