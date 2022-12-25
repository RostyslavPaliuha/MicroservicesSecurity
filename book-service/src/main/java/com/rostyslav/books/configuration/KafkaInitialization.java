package com.rostyslav.books.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaInitialization {

    @Value("${kafka.topics.book-arrived: book_arrived}")
    private String bookArrivedTopic;

    @Bean
    public NewTopic createBookErrivedTopic() {
        return new NewTopic(bookArrivedTopic, 1, (short) 1);
    }
}
