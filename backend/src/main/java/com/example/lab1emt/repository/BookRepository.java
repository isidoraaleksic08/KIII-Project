package com.example.lab1emt.repository;

import com.example.lab1emt.dto.BooksByAuthorView;
import com.example.lab1emt.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findTop10ByOrderByDateDesc();
    @Query("SELECT new com.example.lab1emt.dto.BooksByAuthorView(b.author.id, b.author.name, b.author.surname, COUNT(b)) " +
            "FROM Book b " +
            "GROUP BY b.author.id, b.author.name, b.author.surname")
    List<BooksByAuthorView> getBooksByAuthor();
}
