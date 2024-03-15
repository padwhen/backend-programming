package com.example.bookstore;

import com.example.bookstore.domain.*;
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
	public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository, AppUserRepository userRepository) {
		return (args) -> {
			Category c1 = new Category("Fiction");
			Category c2 = new Category("Non-fiction");
			categoryRepository.save(c1);
			categoryRepository.save(c2);
			Book b1 = new Book("Harry Potter", "J.K Rowling", "2023", "25", c1);
			Book b2 = new Book("Twilight", "Stephenie Meyer", "2024", "25", c1);
			Book b3 = new Book("Game of Thrones", "George R. R. Martin", "2025", "25", c1);
			repository.save(b1);
			repository.save(b2);
			repository.save(b3);
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
		};
	}
}
