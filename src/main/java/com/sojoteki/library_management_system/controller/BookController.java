package com.sojoteki.library_management_system.controller;

import com.sojoteki.library_management_system.model.Book;
import com.sojoteki.library_management_system.request_dto.BookRequestDto;
import com.sojoteki.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<?>  saveBook(@RequestBody BookRequestDto bookRequestDto){
        try{
            String response = bookService.saveBook(bookRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass()+":\n"+"Save operation failed - "+e.getMessage());
        }
    }

    @GetMapping("")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id){
        try {
            Book response = bookService.getBookById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getClass()+":\n"+"Get operation failed - "+e.getMessage());
        }
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<?> getBookByTitle(@RequestParam String title){
        try {
            Book response = bookService.getBookByTitle(title);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getClass()+":\n"+"Get operation failed - "+e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody BookRequestDto bookRequestDto){
        try{
            String response = bookService.updateBook(id, bookRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getClass()+":\n"+"Update operation failed - "+e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id){
        try{
            String response = bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getClass()+":\n"+"Delete operation failed - "+e.getMessage());
        }
    }
}
