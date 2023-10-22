package com.lesson9.bandlist.exception;

public class BandNotFoundException extends RuntimeException {
    public BandNotFoundException(String message) {
        super(message);
    }
}
