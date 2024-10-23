package com.immilog.chatserver.chat.infra.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
            .baseUrl("https://komeet-back-api.com")
            .defaultHeader("Content-Type", "application/json")
    }
}
