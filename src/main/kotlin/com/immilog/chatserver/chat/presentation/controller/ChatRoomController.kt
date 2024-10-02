package com.immilog.chatserver.chat.presentation.controller

import com.immilog.chatserver.chat.application.ChatRoomService
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.presentation.response.ChatApiResponse
import io.swagger.annotations.ApiOperation
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequiredArgsConstructor
class ChatRoomController {
    private val chatRoomService: ChatRoomService? = null

    @ApiOperation(value = "채팅방 생성/조회", notes = "채팅방을 생성하거나 조회해옵니다.")
    @PostMapping("/rooms")
    fun getChatRoom(
        @RequestParam(value = "userSeq") userSeq: Long,
        @RequestParam counterpartSeq: Long
    ): ResponseEntity<ChatApiResponse> {
        val chatRoomSeq: Long = chatRoomService?.getChatRoom(userSeq, counterpartSeq) ?: 0L
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ChatApiResponse.of(chatRoomSeq))
    }

    data class ChatRoomResult(
        val seq: Long? = null,
        val sender: User? = null,
        val recipient: User? = null,
        val lastChat: String? = null,
        val unreadCountForSender: Int? = null,
        val unreadCountForRecipient: Int? = null,
        val lastChatTime: LocalDateTime? = null
    )

}