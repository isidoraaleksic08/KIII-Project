package com.example.lab1emt.config;

import com.example.lab1emt.model.domain.*;
import com.example.lab1emt.model.enumerations.Role;
import com.example.lab1emt.repository.AuthorRepository;
import com.example.lab1emt.repository.BookRepository;
import com.example.lab1emt.repository.CountryRepository;
import com.example.lab1emt.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;

    public DataInitializer(PasswordEncoder passwordEncoder,
                           AuthorRepository authorRepository,
                           BookRepository bookRepository,
                           CountryRepository countryRepository,
                           UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        if (authorRepository.count() > 0 || bookRepository.count() > 0 || countryRepository.count() > 0) {
            return;
        }


        Country country1 = new Country("Country1", "Continent1");
        Country country2 = new Country("Country2", "Continent2");
        Country country3 = new Country("Country3", "Continent3");
        List<Country> countries = List.of(country1, country2, country3);
        countryRepository.saveAll(countries);

        // ✍️ Автори
        Author author1 = new Author("Name1", "Surname1", country2);
        Author author2 = new Author("Name2", "Surname2", country3);
        Author author3 = new Author("Name3", "Surname3", country1);
        List<Author> authors = List.of(author1, author2, author3);
        authorRepository.saveAll(authors);

        // 📚 Категории (ако ти требаат динамични, инаку Enum ќе си стои)
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book1", Category.DRAMA, author2, 3, LocalDateTime.of(2020, 5, 8, 5, 15)));
        books.add(new Book("Book2", Category.NOVEL, author3, 6, LocalDateTime.of(2024, 3, 2, 4, 12)));
        books.add(new Book("Book3", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));
        books.add(new Book("Book4", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));
        books.add(new Book("Book5", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));
        books.add(new Book("Book6", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));
        books.add(new Book("Book7", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));
        books.add(new Book("Book8", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));
        books.add(new Book("Book9", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));
        books.add(new Book("Book10", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));
        books.add(new Book("Book11", Category.BIOGRAPHY, author1, 2, LocalDateTime.of(2025, 3, 4, 1, 23)));

        bookRepository.saveAll(books);


        userRepository.save(new User(
                "at",
                passwordEncoder.encode("at"),
                "Ana",
                "Todorovska",
                Role.ROLE_Librarian
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "User",
                "User",
                Role.ROLE_USER
        ));
    }
}
