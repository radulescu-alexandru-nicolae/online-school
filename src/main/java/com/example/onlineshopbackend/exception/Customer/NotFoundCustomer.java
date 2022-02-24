package com.example.onlineshopbackend.exception.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "not found customer")
public class NotFoundCustomer extends RuntimeException{
    public NotFoundCustomer(String message){
        super(message);
    }
}
