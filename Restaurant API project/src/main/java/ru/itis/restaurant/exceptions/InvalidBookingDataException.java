package ru.itis.restaurant.exceptions;

public class InvalidBookingDataException extends NotFoundExceptionEntity {
    public InvalidBookingDataException() {
        super("You cannot book table at this time, because it's already booked");
    }
}
