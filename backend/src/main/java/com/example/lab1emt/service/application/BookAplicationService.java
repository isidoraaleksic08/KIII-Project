package com.example.lab1emt.service.application;

import com.example.lab1emt.dto.*;
import com.example.lab1emt.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookAplicationService {
    List<DisplayBookDto> getAllBooks();
    Optional<DisplayBookDto> getBookId(Long id);
    Optional<DisplayBookDto>createBook(CreateBookDto createBookDto);
    Optional<DisplayBookDto>updateBook(Long id, UpdateBookDto updateBookDto);
    Optional <DisplayBookDto> markBook(Long id);
    void deletById(Long id);
    List<Book> getLatestBooks();

    List<BooksByAuthorView> getBooksByAuthor();

}
