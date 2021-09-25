package com.simongarton.factorio.exceptions;

public class MakerNotFoundException extends RuntimeException{
    public MakerNotFoundException(String message) {
        super(message);
    }
}
