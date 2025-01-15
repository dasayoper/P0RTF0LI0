package by.dasayoper.taskmanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomOptimisticLockException extends RuntimeException {
    private final HttpStatus httpStatus;

    public CustomOptimisticLockException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
