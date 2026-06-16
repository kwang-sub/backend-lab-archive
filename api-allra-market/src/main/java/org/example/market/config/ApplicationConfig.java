package org.example.market.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Clock;

@Configuration
public class ApplicationConfig {

    @Value("${app.url.payment}")
    private String paymentUrl;

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean(name = "paymentWebClient")
    public WebClient paymentWebClient() {
        return WebClient.builder()
                .baseUrl(paymentUrl)
                .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                    System.out.println("Request Headers: " + clientRequest.headers());
                    System.out.println("Request Body: " + clientRequest.body());
                    return Mono.just(clientRequest);
                }))
                .build();
    }
}
