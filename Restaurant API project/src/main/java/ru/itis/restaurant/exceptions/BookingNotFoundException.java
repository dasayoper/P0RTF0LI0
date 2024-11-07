package ru.itis.restaurant.exceptions;

public class BookingNotFoundException extends NotFoundExceptionEntity {
    public BookingNotFoundException (String message){
        super(message);
    }

}
