package com.immilog.chatserver.chat.presentation.response

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