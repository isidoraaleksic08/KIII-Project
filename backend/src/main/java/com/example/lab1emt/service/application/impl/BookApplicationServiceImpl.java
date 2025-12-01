package com.example.lab1emt.service.application.impl;

import com.example.lab1emt.dto.*;
import com.example.lab1emt.model.domain.Author;
import com.example.lab1emt.model.domain.Book;
import com.example.lab1emt.repository.BookRepository;
import com.example.lab1emt.service.application.BookAplicationService;
import com.example.lab1emt.service.domain.AuthorService;
import com.example.lab1emt.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookAplicationService {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final AuthorService authorService;


    public BookApplicationServiceImpl(BookService bookService, BookRepository bookRepository, AuthorService authorService) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookDto> getAllBooks() {
        return bookService.findAll().stream().map(DisplayBookDto::from).collect(Collectors.toList());

    }
    public List<BooksByAuthorView> getBooksByAuthor() {
        return bookRepository.getBooksByAuthor();
    }

    @Override
    public Optional<DisplayBookDto> getBookId(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> createBook(CreateBookDto createBookDto) {

        Optional<Author> author = authorService.findById(createBookDto.authorId());
        if (author.isEmpty()) {
            return Optional.empty();
        }


        return bookService.create(createBookDto.name(), createBookDto.category(), createBookDto.authorId(), createBookDto.availableCopies(), createBookDto.date())
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> updateBook(Long id, UpdateBookDto updateBookDto) {
        Optional<Author> author = authorService.findById(updateBookDto.authorId());

        if (author.isEmpty()) {
            return Optional.empty();
        }

        return bookService.update(
                id,
                updateBookDto.name(),
                updateBookDto.category(),
                updateBookDto.authorId(),
                updateBookDto.availableCopies()
        ).map(DisplayBookDto::from);
    }


    @Override
    public Optional<DisplayBookDto> markBook(Long id) {
        return bookService.markBook(id)
                .map(DisplayBookDto::from);
    }

    @Override
    public void deletById(Long id) {
         bookService.deleteById(id);
    }
    public List<Book> getLatestBooks() {
        return bookService.getLatestBooks();
    }
}
