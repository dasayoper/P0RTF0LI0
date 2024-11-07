package ru.itis.restaurant.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class NotFoundExceptionEntity extends RuntimeException {
    public NotFoundExceptionEntity(String message) {
        super(message);
    }
}
