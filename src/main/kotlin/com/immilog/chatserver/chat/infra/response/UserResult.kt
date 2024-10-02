package com.immilog.chatserver.chat.infra.response

import java.sql.Date

data class UserResult(
    val seq: Long? = null,
    val nickName: String? = null,
    val email: String? = null,
    val profileImage: String? = null,
    val reportedCount: Long? = null,
    val reportedDate: Date? = null,
    val country: String? = null,
    val interestCountry: String? = null,
    val region: String? = null,
    val userRole: String? = null,
    val userStatus: String? = null
)