package com.agussuhardi.springdemofull.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@AllArgsConstructor
public class CustomException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private String message;

    public CustomException(HttpStatus httpStatus) {
        this.message = httpStatus.getReasonPhrase();
        this.httpStatus = httpStatus;
    }
}
