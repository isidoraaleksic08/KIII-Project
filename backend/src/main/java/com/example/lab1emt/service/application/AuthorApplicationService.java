package com.example.lab1emt.service.application;

import com.example.lab1emt.dto.CreateAuthorDto;
import com.example.lab1emt.dto.DisplayAuthorDto;
import com.example.lab1emt.dto.UpdateAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> getAllAuthors();
    Optional<DisplayAuthorDto> getAuthorById(Long id);
    Optional<DisplayAuthorDto> createAuthor(CreateAuthorDto dto);
    Optional<DisplayAuthorDto> updateAuthor(Long id, UpdateAuthorDto dto);
    void deleteAuthor(Long id);
}
