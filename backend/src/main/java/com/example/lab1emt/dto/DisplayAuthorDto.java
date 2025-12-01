package com.example.lab1emt.dto;

import com.example.lab1emt.model.domain.Author;

public record DisplayAuthorDto(Long id, String name, String surname, Long countryId) {
    public static DisplayAuthorDto from(Author author){
        return new DisplayAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry().getId() );
    }
}
