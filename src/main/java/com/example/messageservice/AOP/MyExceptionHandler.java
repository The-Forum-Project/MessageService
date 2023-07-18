package com.example.messageservice.AOP;

import com.example.messageservice.dto.common.GeneralResponse;
import com.example.messageservice.exception.InvalidAuthorityException;
import com.example.messageservice.exception.InvalidTokenException;
import com.example.messageservice.exception.MessageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {MessageNotFoundException.class})
    public ResponseEntity<GeneralResponse> handleInvalidCredentialsException(MessageNotFoundException e){
        return new ResponseEntity(GeneralResponse.builder().statusCode("400").message(e.getMessage()).build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {InvalidTokenException.class})
    public ResponseEntity<GeneralResponse> handleInvalidTokenException(InvalidTokenException e){
        return new ResponseEntity(GeneralResponse.builder().statusCode("403").message(e.getMessage()).build(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(value = {InvalidAuthorityException.class})
    public ResponseEntity<GeneralResponse> handleInvalidAuthorityException(InvalidAuthorityException e){
        return new ResponseEntity(GeneralResponse.builder().statusCode("403").message(e.getMessage()).build(), HttpStatus.FORBIDDEN);
    }
}
