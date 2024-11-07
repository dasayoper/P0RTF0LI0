package ru.itis.restaurant.exceptions;

public class ItemNotFoundException extends NotFoundExceptionEntity {
    public ItemNotFoundException (String message){
        super(message);
    }
}
