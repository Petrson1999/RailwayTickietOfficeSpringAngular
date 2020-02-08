package com.railvayticketiffice.exception;

import com.railvayticketiffice.data.responses.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ExceptionResponse> handleException(RequestException ex) {
        LOG.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ExceptionResponse.createExceptionResponse(ex));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> handleException(SQLException ex) {
        ExceptionResponse message = createMessage(HttpStatus.BAD_REQUEST);
        message.setMessage("Invalid input data");

        LOG.error(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }

    private ExceptionResponse createMessage(HttpStatus httpStatus) {
        ExceptionResponse message = new ExceptionResponse();
        message.setStatus(httpStatus.value());
        message.setError(httpStatus.name());
        message.setTimestamp(System.currentTimeMillis());
        return message;
    }
}
