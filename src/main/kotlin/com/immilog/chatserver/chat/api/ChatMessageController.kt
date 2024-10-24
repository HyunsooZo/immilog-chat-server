package com.immilog.chatserver.chat.api

import com.immilog.chatserver.chat.api.model.ChatApiResponse
import com.immilog.chatserver.chat.service.ChatMessageService
import com.immilog.chatserver.chat.service.model.ChatMessageResult
import io.swagger.annotations.ApiOperation
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
class ChatMessageController(
    private val chatMessageService: ChatMessageService
) {

    @ApiOperation(value = "채팅 메시지 목록 조회", notes = "특정 채팅방의 메시지 목록을 페이징하여 조회합니다.")
    @GetMapping("/rooms/{chatRoomSeq}/messages")
    fun getChatMessages(
        @PathVariable("chatRoomSeq") chatRoomSeq: Long,
        @RequestParam("page", defaultValue = "0") page: Int
    ): Mono<ResponseEntity<ChatApiResponse<Page<ChatMessageResult>>>> {
        val pageable: Pageable = PageRequest.of(page, 20)
        return chatMessageService.getChatMessages(chatRoomSeq, pageable)
            .map { pageResult ->
                ResponseEntity.ok(ChatApiResponse(data = pageResult))
            }
    }

}
