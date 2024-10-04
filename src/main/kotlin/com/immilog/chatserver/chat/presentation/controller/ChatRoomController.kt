package com.immilog.chatserver.chat.presentation.controller

import com.immilog.chatserver.chat.application.ChatRoomService
import com.immilog.chatserver.chat.domain.model.User
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
        @RequestParam(value = "user") user: User,
        @RequestParam counterpartUser: User
    ): ResponseEntity<ChatApiResponse> {
        val chatRoom = chatRoomService?.getChatRoom(user, counterpartUser)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ChatApiResponse.of(chatRoom))
    }

    data class ChatRoomResult(
        val seq: String? = null,
        val sender: User? = null,
        val recipient: User? = null,
        val lastChat: String? = null,
        val unreadCountForSender: Int? = null,
        val unreadCountForRecipient: Int? = null,
        val lastChatTime: LocalDateTime? = null
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