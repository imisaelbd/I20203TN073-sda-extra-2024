package mbd.dev.restextrambd.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.logging.Logger;

@RestControllerAdvice

public class GlobalExceptionHandler {
    Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.warning(ex.getMessage());
        return new ResponseEntity<>(
                new ApiResponse<>(
                        null, 400, true, Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
