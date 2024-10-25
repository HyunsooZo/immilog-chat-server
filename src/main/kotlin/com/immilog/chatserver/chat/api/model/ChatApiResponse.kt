package com.immilog.chatserver.chat.api.model

import com.mongodb.lang.Nullable

data class ChatApiResponse<out T>(
    val status: Int = 200,
    val message: String = "success",
    @Nullable val data: T? = null
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
