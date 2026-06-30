package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.model.Book;
import com.sojoteki.library_management_system.repository.BookRepository;
import com.sojoteki.library_management_system.request_dto.BookRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public String saveBook(BookRequestDto bookRequestDto){
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setPublisherName(bookRequestDto.getPublisherName());
        book.setPublishedDate(bookRequestDto.getPublishedDate());
        book.setPages(bookRequestDto.getPages());
        book.setAvailability(bookRequestDto.isAvailability());
        book.setCategory(bookRequestDto.getCategory());
        book.setRackNo(bookRequestDto.getRackNo());

        bookRepository.save(book);

        return "Book saved successfully!!";
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(int bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            return book.get();
        }else{
            throw new RuntimeException("Book with id " + bookId + " not found");
        }
    }

    public Book getBookByTitle(String title){
        Optional<Book> book = bookRepository.getBookByTitle(title);

        if(book.isPresent()){
            return book.get();
        }else{
            throw new RuntimeException("Book with title " + title + " not found");
        }
    }

    public String updateBook(int bookId, BookRequestDto bookRequestDto){
        Book book = getBookById(bookId);

        if(book != null){
            book.setTitle(bookRequestDto.getTitle());
            book.setPublisherName(bookRequestDto.getPublisherName());
            book.setPublishedDate(bookRequestDto.getPublishedDate());
            book.setPages(bookRequestDto.getPages());
            book.setAvailability(bookRequestDto.isAvailability());
            book.setCategory(bookRequestDto.getCategory());
            book.setRackNo(bookRequestDto.getRackNo());
            bookRepository.save(book);
            return "Book updated successfully!!";
        }

        throw new RuntimeException("Book with id " + bookId + " not found");
    }

    public String deleteBook(int bookId){
        Book book = getBookById(bookId);
        if(book != null){
            bookRepository.delete(book);
            return "Book deleted successfully!!";
        }
        throw new RuntimeException("Book with id " + bookId + " not found");
    }
}
