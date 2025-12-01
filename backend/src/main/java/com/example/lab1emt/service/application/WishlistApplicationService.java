package com.example.lab1emt.service.application;
import com.example.lab1emt.dto.WishlistDto;

import java.util.Optional;

public interface WishlistApplicationService {
    Optional<WishlistDto> getWishlist(String username);
    Optional<WishlistDto> addToWishlist(String username, Long bookId);
    void rentAllBooks(String username);
}