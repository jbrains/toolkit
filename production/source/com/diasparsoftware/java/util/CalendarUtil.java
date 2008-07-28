package com.diasparsoftware.java.util;

import java.util.*;


public class CalendarUtil {

    public static GregorianCalendar makeGregorianCalendar(
        int year,
        int month,
        int day,
        int hour,
        int minute,
        int second,
        int millisecond) {
    
        GregorianCalendar calendar =
            new GregorianCalendar(
                year,
                month - 1,
                day,
                hour,
                minute,
                second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar;
    }
}
