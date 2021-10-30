package com.simongarton.factorio.exceptions;

public class RecipeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7663916052711068805L;

    public RecipeNotFoundException(final String message) {
        super(message);
    }
}
