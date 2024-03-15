package com.example.bookstore;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	private BookController bookController;
	@Autowired
	private UserController userController;
	@Test
	public void contextLoads() throws Exception {
		assertThat(bookController).isNotNull();
		assertThat(userController).isNotNull();
	}
}
