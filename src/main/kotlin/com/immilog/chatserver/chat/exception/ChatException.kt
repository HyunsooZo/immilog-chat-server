package com.immilog.chatserver.chat.exception

class ChatException(error: ChatErrorCode) : RuntimeException(error.message)