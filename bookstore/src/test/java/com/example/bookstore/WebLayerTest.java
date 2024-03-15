package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    public void testLoginPageContent() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<form")))
                .andExpect(content().string(containsString("<input type=\"text\" name=\"username\"")))
                .andExpect(content().string(containsString("<input type=\"password\" name=\"password\"")))
                .andExpect(content().string(containsString("<input type=\"submit\" value=\"Sign In\"")))
                .andExpect(content().string(containsString("<a href=\"/signup\">Sign-up</a>")));
    }
}

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    public void createNewBook() {
        Category category = new Category("Self-help");
        categoryRepository.save(category);
        Book book = new Book("Big Feelings", "Liz Fossilien & Mollie West Duffy", "2023", "17", category);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }
    @Test
    public void findByTitleShouldReturnAuthor() {
        List<Book> books = bookRepository.findByTitle("Twilight");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Stephenie Meyer");
    }
    @Test
    public void deleteBook() {
        List<Book> books = bookRepository.findByTitle("Twilight");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> newBooks = bookRepository.findByTitle("Twilight");
        assertThat(newBooks).hasSize(0);
    }

}