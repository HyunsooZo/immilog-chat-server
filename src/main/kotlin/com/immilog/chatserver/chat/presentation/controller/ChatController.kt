package com.immilog.chatserver.chat.presentation.controller

import com.immilog.chatserver.chat.application.ChatService
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.presentation.response.ChatApiResponse
import io.swagger.annotations.ApiOperation
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequiredArgsConstructor
class ChatController {
    private final val chatService: ChatService? = null

    /**
     * 채팅 내역 조회
     */
    @ApiOperation(value = "채팅 내역 조회", notes = "채팅 내역을 조회합니다.")
    @GetMapping("/rooms/{chatRoomSeq}")
    fun getChats(
        @PathVariable chatRoomSeq: Long?,
        @RequestParam(value = "userSeq") userSeq: Long,
        @RequestParam(value = "page", defaultValue = "0") page: Int?
    ): ResponseEntity<ChatApiResponse> {
        TODO("not implemented")
    }

    @ApiOperation(value = "채팅 추가", notes = "채팅을 추가합니다.")
    @PostMapping("/rooms/{chatRoomSeq}")
    fun addChat(
        @PathVariable chatRoomSeq: Long?,
        @RequestParam(value = "userSeq") userSeq: Long,
        @RequestBody content: ChatContentRequest?
    ): ResponseEntity<ChatApiResponse> {
        TODO("not implemented")
    }

    data class ChatContentRequest(
        var content: String? = null,
        val attachments: List<String>? = null
    )

    data class ChatResult(
        val id: Long? = null,
        val chatRoomSeq: Long? = null,
        val content: String? = null,
        val sender: User? = null,
        val recipient: User? = null,
        val readStatus: Boolean? = null,
        val attachments: List<String>? = null,
        val createdAt: LocalDateTime? = null
    )
}