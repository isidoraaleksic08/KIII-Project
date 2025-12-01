package com.example.lab1emt.service.application;

import com.example.lab1emt.dto.CreateCountryDto;
import com.example.lab1emt.dto.DisplayCountryDto;
import com.example.lab1emt.dto.UpdateCountryDto;
import com.example.lab1emt.model.domain.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CountryAplicationService {
    List<DisplayCountryDto> getAllCounties();
    Optional<DisplayCountryDto> getCountryId(Long id);
    Optional<DisplayCountryDto>createCountry(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> updateCountry(Long id, UpdateCountryDto updateCountryDto);
    void deleteById(Long id);

}
