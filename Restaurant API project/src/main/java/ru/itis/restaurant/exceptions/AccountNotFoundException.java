package ru.itis.restaurant.exceptions;

public class AccountNotFoundException extends NotFoundExceptionEntity {
    public AccountNotFoundException (String message){
        super(message);
    }

}
