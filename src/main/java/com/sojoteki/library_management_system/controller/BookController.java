package com.sojoteki.library_management_system.controller;

import com.sojoteki.library_management_system.model.Book;
import com.sojoteki.library_management_system.request_dto.BookRequestDto;
import com.sojoteki.library_management_system.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<String> saveBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        String response = bookService.saveBook(bookRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/getByTitle")
    public Book getBookByTitle(@RequestParam String title) {
        return bookService.getBookByTitle(title);
    }

    @PutMapping("/update/{id}")
    public String updateBook(@PathVariable int id, @Valid @RequestBody BookRequestDto bookRequestDto) {
        return bookService.updateBook(id, bookRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }
}
