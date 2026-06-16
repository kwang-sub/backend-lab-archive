package org.example.market.client;

import org.example.market.dto.request.PaymentReqDto;
import org.example.market.dto.response.PaymentResDto;
import org.example.market.enums.TransactionStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class PaymentClient {

    private final WebClient paymentWebClient;

    public PaymentClient(@Qualifier("paymentWebClient") WebClient paymentWebClient) {
        this.paymentWebClient = paymentWebClient;
    }

    public PaymentResDto requestPayment(final PaymentReqDto dto) {
        PaymentResDto response;
        try {
            response = this.sendPaymentRequest(dto);
        } catch (Exception e) {
            response = new PaymentResDto(TransactionStatus.FAILED, null, e.getMessage());
        }

        return response;
    }

    private PaymentResDto sendPaymentRequest(final PaymentReqDto dto) {
        return paymentWebClient.post()
                .uri("/api/v1/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(PaymentResDto.class)
                .block();
    }

    public PaymentResDto requestPaymentCancel(final String transactionId) {
        PaymentResDto response;
        try {
            response = this.sendPaymentCancelRequest(transactionId);
        } catch (Exception e) {
            response = new PaymentResDto(TransactionStatus.FAILED, null, e.getMessage());
        }

        return response;
    }

    private PaymentResDto sendPaymentCancelRequest(final String transactionId) {
        Map<String, String> requestBody = Map.of("transactionId", transactionId);
        return paymentWebClient.post()
                .uri("/api/v1/payment/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(PaymentResDto.class)
                .block();
    }
}
