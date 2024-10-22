package com.immilog.chatserver.chat.exception

class ChatException : RuntimeException {
    constructor() : super()
    constructor(error: ChatErrorCode) : super(error.message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}