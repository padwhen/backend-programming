package com.example.bookstore.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Long categoryId;
        private String name;
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
        private List<Book> books;
        public Category() {}
        public Category(String name) {
            super();
            this.name = name;
        }
        public Long getCategoryId() {
            return categoryId;
        }
        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public List<Book> getBooks() {
            return books;
        }
        public void setBooks(List<Book> books) {
            this.books = books;
        }
        @Override
        public String toString() {
            return name;
        }
}
