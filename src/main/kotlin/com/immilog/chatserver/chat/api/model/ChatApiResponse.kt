package com.immilog.chatserver.chat.api.model


data class ChatApiResponse<T>(
    val status: Int = 200,
    val message: String = "success",
    val data: T? = null
) {
    companion object {
        fun <T> of(data: T): ChatApiResponse<T> {
            return ChatApiResponse(data = data)
        }

        fun <T> error(status: Int, message: String): ChatApiResponse<T> {
            return ChatApiResponse(status = status, message = message)
        }
    }
}