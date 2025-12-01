package com.example.lab1emt.service.domain;

import com.example.lab1emt.model.domain.Wishlist;

import java.util.Optional;

public interface WishlistService {
    Optional<Wishlist> findByUserUsername(String username);
    Wishlist createWishlist(String username);
    Wishlist addBookToWishlist(String username, Long bookId);
    void rentAllBooks(String username);
    void save(Wishlist wishlist);
}
