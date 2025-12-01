package com.example.lab1emt.service.domain.impl;

import com.example.lab1emt.model.domain.Book;
import com.example.lab1emt.model.domain.Category;
import com.example.lab1emt.model.exeptions.InvalidAuthorId;
import com.example.lab1emt.model.exeptions.InvalidBookIdException;
import com.example.lab1emt.repository.BookRepository;
import com.example.lab1emt.service.domain.AuthorService;
import com.example.lab1emt.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();

    }


    @Override

    public Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies, LocalDateTime date) {
        return this.authorService.findById(authorId).map(author -> {
            Book book = new Book(name, category, author, availableCopies, date);
            return Optional.of(this.bookRepository.save(book));
        }).orElse(Optional.empty());
    }


    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new));
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = findById(id).orElseThrow(InvalidAuthorId::new);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(this.authorService.findById(authorId).orElseThrow(InvalidAuthorId::new));
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }


    @Override
    public void deleteById(Long id) {

        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markBook(Long id) {
        return bookRepository.findById(id).map(book -> {
            book.markAsRented();
            return bookRepository.save(book);
        });
    }
    public List<Book> getLatestBooks() {
        return bookRepository.findTop10ByOrderByDateDesc();
    }

}
