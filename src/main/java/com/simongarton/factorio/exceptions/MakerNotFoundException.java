package com.simongarton.factorio.exceptions;

public class MakerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6877762600304163165L;

    public MakerNotFoundException(final String message) {
        super(message);
    }
}
