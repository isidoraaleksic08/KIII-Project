package com.example.lab1emt.service.domain;

import com.example.lab1emt.model.domain.Book;
import com.example.lab1emt.model.domain.Category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies, LocalDateTime date);
    Optional<Book> findById(Long id);

    Optional<Book> update(Long id,String name, Category category, Long authorId, Integer availableCopies);
    void deleteById(Long id);
    Optional<Book> markBook(Long id);
    List<Book> getLatestBooks();
}
