package com.simongarton.factorio.exceptions;

public class IncompleteDataException extends RuntimeException {
    private static final long serialVersionUID = 4062423181923059243L;

    public IncompleteDataException(final String message) {
        super(message);
    }
}
