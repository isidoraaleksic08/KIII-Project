package com.example.lab1emt.web.contorllers;


import com.example.lab1emt.dto.CreateAuthorDto;
import com.example.lab1emt.dto.DisplayAuthorDto;
import com.example.lab1emt.dto.UpdateAuthorDto;
import com.example.lab1emt.model.projection.AuthorNameProjection;
import com.example.lab1emt.model.view.AuthorsByCountryView;
import com.example.lab1emt.repository.AuthorRepository;
import com.example.lab1emt.repository.AuthorsByCountryViewRepository;
import com.example.lab1emt.service.application.AuthorApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@Tag(name = "Author API", description = "Endpoints for managing authors") // OpenAPI tag

public class AuthorController {
    private final AuthorApplicationService authorApplicationService;

    private final AuthorRepository authorRepository;
    private final AuthorsByCountryViewRepository viewRepo;
    public AuthorController(AuthorApplicationService authorApplicationService, AuthorRepository authorRepository, AuthorsByCountryViewRepository viewRepo) {
        this.authorApplicationService = authorApplicationService;
        this.authorRepository = authorRepository;

        this.viewRepo = viewRepo;
    }

    @Operation(summary = "Get all authors", description = "Retrieves a list of all available authors.")
    @GetMapping
    public List<DisplayAuthorDto>findAll(){
        return authorApplicationService.getAllAuthors();
    }


    @Operation(summary = "Get author by ID", description = "Finds a author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.getAuthorById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Add a new author", description = "Creates a new author.")

    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> create(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.createAuthor(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete  author", description = "Deletes a author by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (authorApplicationService.getAuthorById(id).isPresent()) {
            authorApplicationService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Update an existing author", description = "Updates author by ID.")

    @PostMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> update(
            @PathVariable Long id,
            @RequestBody UpdateAuthorDto updateAuthorDto
    ) {
        return authorApplicationService.updateAuthor(id, updateAuthorDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/by-country")
    public List<AuthorsByCountryView> getAuthorsByCountry() {
        return viewRepo.findAll();
    }

    @GetMapping("/names")
    public List<AuthorNameProjection> getAuthorNames() {
        return authorRepository.findAllBy();
    }
}
