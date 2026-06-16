package com.example.catalogservice.messagequeue;

import com.example.catalogservice.entity.Catalog;
import com.example.catalogservice.repository.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CatalogRepository catalogRepository;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage) {
        log.info("kafka Message: -> {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Catalog catalog = catalogRepository.findByProductId((String) map.get("productId"))
                .orElseThrow(() -> new EntityNotFoundException("Catalog does not exist"));

        int qty = catalog.getStock() - (Integer) map.get("qty");
        catalog.setStock(qty);
        catalogRepository.save(catalog);

    }
}
