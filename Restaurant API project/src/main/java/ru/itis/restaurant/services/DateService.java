package ru.itis.restaurant.services;

import java.text.ParseException;

public interface DateService {
    boolean checkDate(String bF, String bT, String dtoF, String dtoT) throws ParseException;

    String reformatDate(String date);
}
