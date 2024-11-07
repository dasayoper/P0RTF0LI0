package ru.itis.restaurant.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotFoundExceptionEntity extends RuntimeException {
    public NotFoundExceptionEntity(String message) {
        super(message);
    }
}
