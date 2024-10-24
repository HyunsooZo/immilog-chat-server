package com.immilog.chatserver.chat.service

import com.immilog.chatserver.chat.domain.model.ChatRoom
import com.immilog.chatserver.chat.domain.model.User
import com.immilog.chatserver.chat.domain.repository.ChatRoomRepository
import com.immilog.chatserver.chat.service.model.ChatRoomResult
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository
) {
    fun getChatRooms(
        user: User,
        pageable: Pageable
    ): Mono<Page<ChatRoomResult>> {
        return chatRoomRepository
            .findChatRoomsByUser(user, pageable)
            .map { page ->
                PageImpl(
                    page.content.map { it.toResult() },
                    pageable,
                    page.totalElements
                )
            }
    }

    fun createChatRoom(participants: List<User>): Mono<ChatRoomResult> {
        val newChatRoomSeq = System.currentTimeMillis()
        val chatRoom = ChatRoom(
            seq = newChatRoomSeq,
            sender = participants[0],
            recipient = participants[1],
            createdAt = java.time.LocalDateTime.now(),
            updatedAt = java.time.LocalDateTime.now()
        )
        return chatRoomRepository
            .findChatRoomsByUser(participants[0], Pageable.unpaged())
            .flatMapMany { Flux.fromIterable(it.content) }
            .filter { existingChatRoom ->
                participants.containsAll(
                    listOf(existingChatRoom.sender, existingChatRoom.recipient)
                )
            }
            .hasElements()
            .flatMap { exists ->
                if (exists) {
                    Mono.error(RuntimeException("ChatRoom already exists"))
                } else {
                    chatRoomRepository.save(chatRoom).map { it.toResult() }
                }
            }
    }
}
