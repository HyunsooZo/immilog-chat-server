package com.immilog.chatserver.chat.api

import com.immilog.chatserver.chat.api.model.ChatApiResponse
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.service.ChatRoomService
import com.immilog.chatserver.chat.service.model.ChatRoomResult
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
class ChatRoomController(
    private val chatRoomService: ChatRoomService
) {
    @ApiOperation(value = "채팅방 목록 조회", notes = "특정 사용자가 참여한 채팅방 목록을 페이징하여 조회합니다.")
    @GetMapping("/rooms")
    fun getChatRooms(
        @RequestParam("userSeq") userSeq: Long,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): Mono<ResponseEntity<ChatApiResponse<Page<ChatRoomResult>>>> {
        val user = User(seq = userSeq)
        val pageable: Pageable = PageRequest.of(page, 10)
        return chatRoomService.getChatRooms(user, pageable)
            .map { pageResult ->
                ResponseEntity.ok(ChatApiResponse(data = pageResult))
            }
    }

    @ApiOperation(value = "채팅방 생성", notes = "새로운 채팅방을 생성합니다.")
    @PostMapping("/rooms")
    fun createChatRoom(
        @RequestBody createChatRoomRequest: CreateChatRoomRequest
    ): Mono<ResponseEntity<ChatApiResponse<ChatRoomResult>>> {
        val participants = createChatRoomRequest.userSeqs.map { seq ->
            User(seq = seq)
        }
        return chatRoomService.createChatRoom(participants)
            .map { chatRoomResult ->
                ResponseEntity.ok(ChatApiResponse(data = chatRoomResult))
            }
    }

    data class CreateChatRoomRequest(
        val userSeqs: List<Long>
    )

}
