package com.example.lab1emt.dto;

import com.example.lab1emt.model.domain.Country;

public record CreateCountryDto (String name, String continent){
    public Country toCountry(){
        return new Country(name,continent);
    }
}
