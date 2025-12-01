package com.example.lab1emt.service.domain.impl;

import com.example.lab1emt.model.domain.Book;
import com.example.lab1emt.model.domain.User;
import com.example.lab1emt.model.domain.Wishlist;
import com.example.lab1emt.model.exeptions.BookNotAvailableException;
import com.example.lab1emt.model.exeptions.UserNotFoundException;
import com.example.lab1emt.repository.BookRepository;
import com.example.lab1emt.repository.UserRepository;
import com.example.lab1emt.repository.WishlistRepository;
import com.example.lab1emt.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.lang.reflect.InaccessibleObjectException;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Wishlist> findByUserUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found"));
        return wishlistRepository.findByUser(user);
    }

    @Override
    public Wishlist createWishlist(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(InaccessibleObjectException::new);
        Wishlist wishlist = new Wishlist(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist addBookToWishlist(String username, Long bookId) {
        Wishlist wishlist = findByUserUsername(username).orElseGet(() -> createWishlist(username));
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotAvailableException::new);

        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException();
        }

        wishlist.addBook(book);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void rentAllBooks(String username) {
        Wishlist wishlist = findByUserUsername(username).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        wishlist.getBooks().forEach(book -> {
            if (book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
            } else {
                throw new BookNotAvailableException();
            }
        });
        wishlist.clearWishlist();
        wishlistRepository.save(wishlist);
    }
    public void save(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

}