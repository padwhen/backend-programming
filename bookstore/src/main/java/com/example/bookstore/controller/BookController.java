package com.example.bookstore.controller;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

        // Show all books
        @RequestMapping(value={"/", "booklist"})
        public String bookList(Model model) {
            model.addAttribute("books", bookRepository.findAll());
            return "booklist";
        }
        // RESTful service to get all books
        @RequestMapping(value="/api/books", method = RequestMethod.GET, produces = "application/json")
        public @ResponseBody List<Book> getAllBooksRest() {
            return (List<Book>) bookRepository.findAll();
        }
        // RESTful service to return one book by id
        @RequestMapping(value="/api/book/{id}", method=RequestMethod.GET, produces = "application/json")
        @ResponseBody
        public ResponseEntity<Book> getBookByIdRest(@PathVariable("id") Long id) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                return ResponseEntity.ok(optionalBook.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        // Add new books
        @RequestMapping(value="/add")
        public String addStudent(Model model) {
            model.addAttribute("book", new Book());
            model.addAttribute("category", categoryRepository.findAll());
            return "addbook";
        }
        @RequestMapping(value="/save",method= RequestMethod.POST)
        public String save(Book book) {
            bookRepository.save(book);
            return "redirect:booklist";
        }
        // Delete existing books
        @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
        public String deleteStudent(@PathVariable("id") Long id, Model model) {
            bookRepository.deleteById(id);
            return "redirect:../booklist";
        }
        // Editing books
        @RequestMapping(value="/edit/{id}")
        public String showModStu(@PathVariable("id") Long id, Model model) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                model.addAttribute("book", optionalBook.get());
                model.addAttribute("category", categoryRepository.findAll());
                return "editbook";
            } else {
                return "error";
            }
        }
        @RequestMapping(value="/login")
        public String login() {
            return "login";
        }
}
