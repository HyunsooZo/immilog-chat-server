package com.immilog.chatserver.chat.domain.model

import java.sql.Date

data class User(
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
) {
    data class Builder(
        var seq: Long? = null,
        var nickName: String? = null,
        var email: String? = null,
        var profileImage: String? = null,
        var reportedCount: Long? = null,
        var reportedDate: Date? = null,
        var country: String? = null,
        var interestCountry: String? = null,
        var region: String? = null,
        var userRole: String? = null,
        var userStatus: String? = null
    ) {
        fun seq(seq: Long) = apply { this.seq = seq }
        fun nickName(nickName: String) = apply { this.nickName = nickName }
        fun email(email: String) = apply { this.email = email }
        fun profileImage(profileImage: String) = apply { this.profileImage = profileImage }
        fun reportedCount(reportedCount: Long) = apply { this.reportedCount = reportedCount }
        fun reportedDate(reportedDate: Date) = apply { this.reportedDate = reportedDate }
        fun country(country: String) = apply { this.country = country }
        fun interestCountry(interestCountry: String) = apply { this.interestCountry = interestCountry }
        fun region(region: String) = apply { this.region = region }
        fun userRole(userRole: String) = apply { this.userRole = userRole }
        fun userStatus(userStatus: String) = apply { this.userStatus = userStatus }
        fun build() = User(
            seq,
            nickName,
            email,
            profileImage,
            reportedCount,
            reportedDate,
            country,
            interestCountry,
            region,
            userRole,
            userStatus
        )
    }
}