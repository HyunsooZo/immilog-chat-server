package com.immilog.chatserver.chat.api

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.time.Duration

@RestController
class SseController {

    private val sink = Sinks.many().multicast().onBackpressureBuffer<String>()

    fun emitEvent(message: String) {
        sink.tryEmitNext(message)
    }

    @GetMapping("/sse/notifications", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamNotifications(): Flux<String> {
        return sink.asFlux().delayElements(Duration.ofMillis(100))
    }
}