package ru.itis.restaurant.exceptions;

public class OrderNotFoundException extends NotFoundExceptionEntity {
    public OrderNotFoundException (String message){
        super(message);
    }
}
