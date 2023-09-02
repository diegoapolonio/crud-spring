package com.diego.controler;

import com.diego.exception.RecordNotFoudException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
@ExceptionHandler(RecordNotFoudException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoudException ex){
        return "error" +ex.getLocalizedMessage();

    }

}
