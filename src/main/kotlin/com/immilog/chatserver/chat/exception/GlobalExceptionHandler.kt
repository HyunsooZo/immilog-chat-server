package com.immilog.chatserver.chat.exception

import com.immilog.chatserver.chat.api.model.ChatApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ChatException::class)
    fun <T> handleChatException(
        ex: ChatException
    ): ResponseEntity<ChatApiResponse<T>> {
        val errorCode = ex.message
        val response = ChatApiResponse(
            status = 400,
            message = errorCode!!,
            data = null
        )
        return ResponseEntity.status(400).body(response)
    }

    @ExceptionHandler(Exception::class)
    fun <T> handleGeneralException(
        ex: Exception
    ): ResponseEntity<ChatApiResponse<T>> {
        val response = ChatApiResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = ex.message ?: "Internal Server Error",
            data = null
        )
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response)
    }

}
