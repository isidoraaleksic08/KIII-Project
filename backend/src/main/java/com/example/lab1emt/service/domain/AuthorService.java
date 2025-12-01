package com.example.lab1emt.service.domain;
import com.example.lab1emt.model.domain.Author;

import java.util.List;
import java.util.Optional;
public interface AuthorService {
    List<Author> findAll();
    Optional<Author> create(String name, String surname, Long countryId);
    Optional<Author> findById(Long id);
    Optional<Author> update(Long id,String name, String surname, Long countryId);

    void deleteById(Long id);
}
