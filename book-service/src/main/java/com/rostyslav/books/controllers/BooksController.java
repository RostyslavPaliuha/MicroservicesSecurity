package com.rostyslav.books.controllers;

import com.rostyslav.books.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/api/v1")
public class BooksController {

    @Value("${adminProperty: default admin property value}")
    private String adminProperty;

    @Value("${userProperty: default user property value}")
    private String userProperty;

    @Value("${kafka.topics.book-arrived: book_arrived}")
    private String bookArrivedTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public BooksController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @GetMapping("/books")
    public ResponseEntity getBookList() {
        List<Book> books = List.of(new Book(), new Book(), new Book(), new Book(), new Book());
        return new ResponseEntity(books, HttpStatus.OK);
    }
    @PostMapping("/books")
    public ResponseEntity addNewBook(@RequestBody Book book) {
        kafkaTemplate.send(bookArrivedTopic,"New book added to inventory. "+ book.getBookName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity admin() {
        String data = "{\"data\":\"Hello Admin! your property is: " + adminProperty + "\"}";
        return new ResponseEntity(data, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity user() {
        String userData = "{\"data\":\"Hello User! your property is: " + userProperty + "\"}";
        return new ResponseEntity(userData, HttpStatus.OK);
    }

    @GetMapping("/guest")
    public ResponseEntity guest() {
        return new ResponseEntity("{\"data\":\"Hello Guest!\"}", HttpStatus.OK);
    }

}

