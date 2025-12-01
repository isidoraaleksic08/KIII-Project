package com.example.lab1emt.dto;

import com.example.lab1emt.model.domain.Author;
import com.example.lab1emt.model.domain.Book;
import com.example.lab1emt.model.domain.Category;

import java.time.LocalDateTime;
import java.util.Date;

public record CreateBookDto(String name, Category category, Long authorId, int availableCopies, LocalDateTime date) {
    public Book toBook( Author author){
        return new Book(name,category,author,availableCopies,date);
    }
}
