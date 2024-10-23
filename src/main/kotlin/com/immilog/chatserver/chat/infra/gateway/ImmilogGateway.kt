package com.immilog.chatserver.chat.infra.gateway

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Slf4j
@Component
class ImmilogGateway(
    private val webClient: WebClient.Builder
) {
    fun sendEventToMainServer(
        eventRequest: EventRequest
    ): Mono<Void> {
        return webClient.build()
            .post()
            .uri("/event")
            .bodyValue(eventRequest)
            .retrieve()
            .bodyToMono(Void::class.java)
            .doOnSuccess {
                println(
                    """
                    message event sent to main server 
                    senderSeq: ${eventRequest.senderSeq} 
                    recipientSeq: ${eventRequest.recipientSeq}
                    """
                )
            }
            .doOnError { error ->
                println(
                    """
                    Failed to send event to main server 
                    senderSeq: ${eventRequest.senderSeq} 
                    recipientSeq: ${eventRequest.recipientSeq}
                    error: $error
                    """
                )
            }
    }

    data class EventRequest(
        val chatRoomSeq: Long,
        val senderSeq: Long,
        val recipientSeq: Long,
        val message: String,
        val attachments: List<String>
    )
}
