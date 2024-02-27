package com.example.bookstore;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Book b1 = new Book("Harry Potter", "J.K Rowling", "2023", "25");
			Book b2 = new Book("Twilight", "Stephenie Meyer", "2024", "25");
			Book b3 = new Book("Game of Thrones", "George R. R. Martin", "2025", "25");
			repository.save(b1);
			repository.save(b2);
			repository.save(b3);
		};
	}
}
