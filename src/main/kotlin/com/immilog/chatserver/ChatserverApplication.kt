package com.immilog.chatserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatserverApplication

fun main(args: Array<String>) {
    runApplication<ChatserverApplication>(*args)
}
