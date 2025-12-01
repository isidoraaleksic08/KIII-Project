package com.example.lab1emt.dto;

import com.example.lab1emt.model.domain.Category;

public record UpdateBookDto(String name, Category category, Long authorId, Integer availableCopies) {
}
