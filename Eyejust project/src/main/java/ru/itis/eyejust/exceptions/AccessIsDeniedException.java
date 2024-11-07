package ru.itis.eyejust.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessIsDeniedException extends RuntimeException {
    public AccessIsDeniedException(String message) {
        super(message);
    }
}