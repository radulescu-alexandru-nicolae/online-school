package com.example.onlineshopbackend.exception.Orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "not found customer")
public class NotFoundOrders extends RuntimeException{
    public NotFoundOrders(String message){
        super(message);
    }

}
