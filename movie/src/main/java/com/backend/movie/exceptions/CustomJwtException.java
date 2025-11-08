package com.backend.movie.exceptions;

public class CustomJwtException extends RuntimeException{
    public CustomJwtException(String msg) {
        super(msg);
    }
}
