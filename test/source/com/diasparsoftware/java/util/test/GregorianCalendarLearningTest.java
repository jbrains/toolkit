package com.diasparsoftware.java.util.test;

import java.util.*;

import junit.framework.TestCase;

public class GregorianCalendarLearningTest extends TestCase {
    public void testSetYear() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));

        assertEquals(0, calendar.getTime().getTime());
    }
}
