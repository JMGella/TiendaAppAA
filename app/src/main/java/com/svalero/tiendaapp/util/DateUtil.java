package com.svalero.tiendaapp.util;

import android.icu.text.SimpleDateFormat;


import java.text.ParseException;
import java.time.LocalDate;



public class DateUtil {


    private final String DATTE_PATTERN = "dd/MM/yyyy";




    public String format(LocalDate date) {
        if (date == null) return "N/A";
        return date.toString();
    }

    public LocalDate parse(String date) throws ParseException {
        return LocalDate.parse(date);
    }

    public LocalDate today(){
        return LocalDate.now();
    }

    public String formatString(String date) throws ParseException {
        LocalDate localDate = parse(date);
        return format(localDate);
    }
}