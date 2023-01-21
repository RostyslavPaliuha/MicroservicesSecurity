package com.rostyslav.books.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book {

    private BigDecimal price;

    private String bookName;

    private String author;
}
