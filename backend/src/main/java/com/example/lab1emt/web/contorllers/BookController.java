package com.example.lab1emt.web.contorllers;

import com.example.lab1emt.dto.BooksByAuthorView;
import com.example.lab1emt.dto.CreateBookDto;
import com.example.lab1emt.dto.DisplayBookDto;
import com.example.lab1emt.dto.UpdateBookDto;
import com.example.lab1emt.model.domain.Book;
import com.example.lab1emt.model.dto.BookDto;
import com.example.lab1emt.service.application.BookAplicationService;
import com.example.lab1emt.service.domain.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book API", description = "Endpoints for managing books") // OpenAPI tag
public class BookController {

    private final BookAplicationService bookAplicationService;

    public BookController( BookAplicationService bookAplicationService) {
        this.bookAplicationService = bookAplicationService;
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all available books.")
    @GetMapping
    public List<DisplayBookDto> findAll() {
        return bookAplicationService.getAllBooks();
    }
    @Operation(summary = "Get book by ID", description = "Finds a book by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookAplicationService.getBookId(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Add a new book", description = "Creates a new book.")
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDto) {
        return bookAplicationService.createBook(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing book", description = "Updates a book by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> update(
            @PathVariable Long id,
            @RequestBody UpdateBookDto updateBookDto
    ) {
        return bookAplicationService.updateBook(id, updateBookDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (bookAplicationService.getBookId(id).isPresent()) {
            bookAplicationService.deletById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/by-author")
    @Operation(summary = "Get number of books by author", description = "Retrieves the number of books per author from the materialized view.")
    public List<BooksByAuthorView> getBooksByAuthor() {
        return bookAplicationService.getBooksByAuthor();
    }
    @Operation(summary = "Mark book as rented", description = "Marks a book as rented by its ID.")
    @PutMapping("/{id}/mark-rented")
    public ResponseEntity<DisplayBookDto> markBookAsRented(@PathVariable Long id) {
        Optional<DisplayBookDto> updatedBook = bookAplicationService.markBook(id);

        return updatedBook
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "List 10 books", description = "List the newest 10 books")

    @GetMapping("/latest")
    public List<Book> getLatestBooks() {
        return bookAplicationService.getLatestBooks();
    }
}
