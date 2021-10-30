package com.simongarton.factorio.exceptions;

public class MoreThanOneMakerException extends RuntimeException {
    private static final long serialVersionUID = 6503131902477544280L;

    public MoreThanOneMakerException(final String message) {
        super(message);
    }
}
