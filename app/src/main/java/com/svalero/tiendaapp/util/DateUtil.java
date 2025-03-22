package com.svalero.tiendaapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static LocalDate format(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static String parse(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
