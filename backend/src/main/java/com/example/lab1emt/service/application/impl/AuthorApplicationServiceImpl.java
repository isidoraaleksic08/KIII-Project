package com.example.lab1emt.service.application.impl;

import com.example.lab1emt.dto.CreateAuthorDto;
import com.example.lab1emt.dto.DisplayAuthorDto;
import com.example.lab1emt.dto.UpdateAuthorDto;

import com.example.lab1emt.model.domain.Country;
import com.example.lab1emt.service.application.AuthorApplicationService;
import com.example.lab1emt.service.domain.AuthorService;
import com.example.lab1emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;
    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayAuthorDto> getAllAuthors() {
        return authorService.findAll().stream().map(DisplayAuthorDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAuthorDto> getAuthorById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> createAuthor(CreateAuthorDto dto) {
      Optional<Country> country = countryService.findById(dto.countryId());
      if(country.isEmpty()){
          return Optional.empty();
      }


      return authorService.create(dto.name(), dto.surname(), dto.countryId()).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> updateAuthor(Long id, UpdateAuthorDto dto) {
       Optional<Country>country=countryService.findById((dto.countryId()));
       if(country.isEmpty()){
           return Optional.empty();
       }
       return authorService.update(id, dto.name(), dto.surname(), dto.countryId()).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteAuthor(Long id) {
authorService.deleteById(id);
    }
}
