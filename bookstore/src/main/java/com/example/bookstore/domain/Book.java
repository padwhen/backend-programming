package com.example.bookstore.domain;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String price;
    public Book(String title, String author, String isbn, String price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }
}
