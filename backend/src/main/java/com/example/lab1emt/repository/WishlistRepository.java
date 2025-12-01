package com.example.lab1emt.repository;

import com.example.lab1emt.model.domain.User;
import com.example.lab1emt.model.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUser(User user);
}