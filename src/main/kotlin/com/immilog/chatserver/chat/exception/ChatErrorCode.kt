package com.immilog.chatserver.chat.exception

import lombok.Getter
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus

@RequiredArgsConstructor
@Getter
enum class ChatErrorCode(
    val status: HttpStatus,
    val message: String
) {
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 채팅방입니다."),
}