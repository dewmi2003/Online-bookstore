package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String listBooks(Model model, @RequestParam(value = "query", required = false) String query) {
        List<Book> books = bookService.searchBooks(query);
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        return "book_details";
    }
}
