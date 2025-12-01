package com.example.lab1emt.dto;

import com.example.lab1emt.model.domain.Country;
import com.example.lab1emt.model.domain.Author;

public record CreateAuthorDto(String name, String surname, Long countryId) {
    public Author toAuthor(Country country){
        return new Author(name, surname, country);
    }
}
