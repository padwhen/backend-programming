package com.example.bookstore.controller;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;
        @RequestMapping(value={"/", "booklist"})
        public String bookList(Model model) {
            model.addAttribute("books", bookRepository.findAll());
            return "booklist";
        }
        @RequestMapping(value="/add")
        public String addStudent(Model model) {
            model.addAttribute("book", new Book());
            return "addbook";
        }
        @RequestMapping(value="/save",method= RequestMethod.POST)
        public String save(Book book) {
            bookRepository.save(book);
            return "redirect:booklist";
        }
        @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
        public String deleteStudent(@PathVariable("id") Long id, Model model) {
            bookRepository.deleteById(id);
            return "redirect:../booklist";
        }
        @RequestMapping(value="/edit/{id}")
        public String showModStu(@PathVariable("id") Long id, Model model) {
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (optionalBook.isPresent()) {
                model.addAttribute("book", optionalBook.get());
                return "editbook";
            } else {
                return "error";
            }
        }
}
