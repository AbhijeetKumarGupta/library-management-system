package com.sojoteki.library_management_system.repository;

import com.sojoteki.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true, value = "select * from library_management_system.book where title = :title")
    Optional<Book> getBookByTitle(String title);
}
