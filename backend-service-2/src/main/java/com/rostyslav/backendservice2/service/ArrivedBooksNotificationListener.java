package com.rostyslav.backendservice2.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ArrivedBooksNotificationListener {

    @KafkaListener(topics = "book_arrived")
    public void processMessage(String notification) {
        log.info(notification);
    }
}
