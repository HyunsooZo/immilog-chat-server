package com.immilog.chatserver.chat.domain.model

data class User(
    val seq: Long? = null,
    val nickName: String? = null,
    val email: String? = null,
    val profileImage: String? = null,
) {
}