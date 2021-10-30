package com.simongarton.factorio.exceptions;

public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3298693556689432558L;

    public ItemNotFoundException(final String message) {
        super(message);
    }
}
