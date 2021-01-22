package com.englishapp.demoen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileNameAlreadyExistException extends RuntimeException{
    public FileNameAlreadyExistException(String message) {
        super(message);
    }
}
