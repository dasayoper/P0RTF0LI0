package ru.itis.eyejust.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DiagnosticException extends RuntimeException {
    public DiagnosticException(String message) { super(message); }
}
