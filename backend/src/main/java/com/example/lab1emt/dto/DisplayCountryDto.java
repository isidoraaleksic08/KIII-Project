package com.example.lab1emt.dto;

import com.example.lab1emt.model.domain.Country;

public record DisplayCountryDto(Long id, String name, String continent) {
    public static DisplayCountryDto from(Country country){
        return new DisplayCountryDto(country.getId() , country.getName(), country.getContinent());
    }
}
