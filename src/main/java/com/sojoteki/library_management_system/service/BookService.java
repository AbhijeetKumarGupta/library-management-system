package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.exception.ResourceNotFoundException;
import com.sojoteki.library_management_system.model.Book;
import com.sojoteki.library_management_system.repository.BookRepository;
import com.sojoteki.library_management_system.request_dto.BookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public String saveBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        applyRequest(book, bookRequestDto);
        bookRepository.save(book);

        return "Book saved successfully!!";
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " not found"));
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Book with title " + title + " not found"));
    }

    public String updateBook(int bookId, BookRequestDto bookRequestDto) {
        Book book = getBookById(bookId);
        applyRequest(book, bookRequestDto);
        bookRepository.save(book);
        return "Book updated successfully!!";
    }

    public String deleteBook(int bookId) {
        Book book = getBookById(bookId);
        bookRepository.delete(book);
        return "Book deleted successfully!!";
    }

    private void applyRequest(Book book, BookRequestDto bookRequestDto) {
        book.setTitle(bookRequestDto.getTitle());
        book.setPublisherName(bookRequestDto.getPublisherName());
        book.setPublishedDate(bookRequestDto.getPublishedDate());
        book.setPages(bookRequestDto.getPages());
        book.setAvailability(bookRequestDto.isAvailability());
        book.setCategory(bookRequestDto.getCategory());
        book.setRackNo(bookRequestDto.getRackNo());
    }
}
