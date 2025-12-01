package com.example.lab1emt.repository;

import com.example.lab1emt.model.view.AuthorsByCountryView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsByCountryViewRepository extends JpaRepository<AuthorsByCountryView, Long> {
}
