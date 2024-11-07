package ru.itis.restaurant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.services.DateService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class DateServiceImpl implements DateService {
    @Override
    public boolean checkDate(String bF, String bT, String dtoF, String dtoT) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date bFrom = sdf.parse(bF);
        Date bTo = sdf.parse(bT);
        Date dtoFrom = sdf.parse(dtoF);
        Date dtoTo = sdf.parse(dtoT);

        return (dtoFrom.compareTo(bFrom) < 0 && dtoTo.compareTo(bFrom) < 0) || (dtoFrom.compareTo(bTo) > 0 && dtoTo.compareTo(bTo) > 0);
    }

    @Override
    public String reformatDate(String date) {
        return date.substring(0, 10) + " " + date.substring(11, 16);
    }
}
