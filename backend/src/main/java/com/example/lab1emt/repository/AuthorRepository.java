package com.example.lab1emt.repository;

import com.example.lab1emt.model.domain.Author;
import com.example.lab1emt.model.projection.AuthorNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query("SELECT DISTINCT a.name AS firstName, a.surname AS lastName FROM Author a")
    List<AuthorNameProjection> findAllBy();
}
