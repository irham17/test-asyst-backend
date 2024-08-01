package com.rungroup.backend.exception;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(Long id){
        super("Could not found the booking with id "+ id);
    }
}
