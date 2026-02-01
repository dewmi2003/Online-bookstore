package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private BookService bookService;
    private CategoryService categoryService;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public AdminController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "admin/books";
    }

    @GetMapping("/books/new")
    public String createBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/book_form";
    }

    @PostMapping("/books")
    public String saveBook(@ModelAttribute("book") Book book,
            @RequestParam("image") MultipartFile startImage) throws IOException {

        if (!startImage.isEmpty()) {
            String fileName = StringUtils.cleanPath(startImage.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (var inputStream = startImage.getInputStream()) {
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                book.setCoverImage(uniqueFileName);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + fileName, ioe);
            }
        }

        bookService.saveBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/books/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        return "admin/book_form";
    }

    @PostMapping("/books/{id}")
    public String updateBook(@PathVariable Long id,
            @ModelAttribute("book") Book book,
            @RequestParam("image") MultipartFile startImage) throws IOException {

        Book existingBook = bookService.findBookById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setDescription(book.getDescription());
        existingBook.setCategory(book.getCategory());

        if (!startImage.isEmpty()) {
            // Logic to update image same as save
            // (Reuse logic ideally, duplicating for brevity as instructed to not skip)
            String fileName = StringUtils.cleanPath(startImage.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);
            try (var inputStream = startImage.getInputStream()) {
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                existingBook.setCoverImage(uniqueFileName);
            }
        }

        bookService.updateBook(existingBook);
        return "redirect:/admin/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }
}
