package com.example.lab1emt.model.exeptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(){
        super("Not Available");
    }
}
