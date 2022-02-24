package com.example.onlineshopbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "bar request")
public class BadRequest extends RuntimeException{
        public BadRequest(String message){
            super(message);
        }


}
