package com.auth.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException e) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Not Found")
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredSessionException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDetails> handlerExpiredSessionException(ExpiredSessionException e) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Expired Session")
                .message(e.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity(exceptionDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDetails> handlerInvalidTokenException(InvalidTokenException e) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Invalid Token")
                .message(e.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity(exceptionDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExistsException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDetails> handlerExistsException(ExistsException e) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Exists Exception")
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectInformationException.class)
    @ResponseBody
    public ResponseEntity<ExceptionDetails> handlerIncorrectInformationException(IncorrectInformationException e) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Incorrect Information")
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
