package com.immilog.chatserver.chat.presentation.controller

import com.immilog.chatserver.chat.application.ChatMessageService
import io.swagger.annotations.ApiOperation
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
class ChatController {
    private final val chatMessageService: ChatMessageService? = null

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

    data class ChatApiResponse(
        var status: Int? = 200,
        var message: String? = "success",
        var data: Any? = null,
        var list: List<Any>? = ArrayList(),
    ) {
        companion object {
            fun <T> of(
                data: T?
            ): ChatApiResponse {
                return ChatApiResponse(200, "success", data)
            }
        }
    }
}