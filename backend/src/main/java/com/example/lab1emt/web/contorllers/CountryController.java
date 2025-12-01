package com.example.lab1emt.web.contorllers;


import com.example.lab1emt.dto.CreateCountryDto;
import com.example.lab1emt.dto.DisplayCountryDto;
import com.example.lab1emt.dto.UpdateCountryDto;
import com.example.lab1emt.model.domain.Country;
import com.example.lab1emt.model.dto.CountryDto;
import com.example.lab1emt.service.application.CountryAplicationService;
import com.example.lab1emt.service.domain.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
@Tag(name = "Country API", description = "Endpoints for managing counties") // OpenAPI tag

public class CountryController {
    private final CountryAplicationService countryAplicationService;

    public CountryController( CountryAplicationService countryAplicationService) {
        this.countryAplicationService = countryAplicationService;

    }


    @Operation(summary = "Get all counties", description = "Retrieves a list of all available counties.")
    @GetMapping
    public List<DisplayCountryDto> findAll() {
        return countryAplicationService.getAllCounties();
    }


    @Operation(summary = "Get country by ID", description = "Finds a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryAplicationService.getCountryId(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> create(@RequestBody CreateCountryDto createCountryDto) {
        return countryAplicationService.createCountry(createCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country", description = "Deletes a country by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryAplicationService.getCountryId(id).isPresent()) {
            countryAplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Update an existing country", description = "Updates a county by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(
            @PathVariable Long id,
            @RequestBody UpdateCountryDto updateCountryDto
    ) {
        return countryAplicationService.updateCountry(id, updateCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

