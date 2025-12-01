package com.example.lab1emt.dto;

import com.example.lab1emt.model.domain.Book;
import com.example.lab1emt.model.domain.Category;

import java.time.LocalDateTime;

public record DisplayBookDto (Long id, String name, Category category, Long authorId, Integer availableCopies,
                              LocalDateTime date) {
 public static DisplayBookDto from(Book book){
     return new DisplayBookDto(book.getId(),book.getName(),book.getCategory(),book.getAuthor().getId(), book.getAvailableCopies(),book.getDate());
 }
}
