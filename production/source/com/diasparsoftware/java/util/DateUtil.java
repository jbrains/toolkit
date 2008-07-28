package com.diasparsoftware.java.util;

import java.util.*;

public class DateUtil {
    private static Calendar calendar = new GregorianCalendar();
    /***
	 * Create a <code>Date</code> object from the parameters you
	 * expect to pass -- none of this "year minus 1900" and "months
	 * start from 0" nonsense and we assume Gregorian!
	 * 
	 * @param year
	 *            The real year: do not subtract 1900
	 * @param month
	 *            The real month: do not subtract 1
	 * @param day
	 *            Yes, we call it "day" and not "date"
	 * @return
	 */
    public static Date makeDate(int year, int month, int day) {
        calendar.clear();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date makeDate(
        int year,
        int month,
        int day,
        int hour,
        int minute,
        int second) {
        calendar.clear();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
    public static int getMonth(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /***
	 * Make a date a specified number of days in the future from the
	 * provided date.
	 * 
	 * @param date
	 *            A benchmark date.
	 * @param daysLater
	 *            The number of days after the benchmark date you want
	 *            to go.
	 * @return
	 */
    public static Date makeFutureDate(Date date, int daysLater) {
        Calendar dateOnCalendar = Calendar.getInstance();
        dateOnCalendar.setTime(date);
        dateOnCalendar.add(Calendar.DATE, daysLater);
        return dateOnCalendar.getTime();
    }

    /***
	 * Compute someone's age from their date of birth and a future
	 * date. If you give a future date <em>not</em> in the future,
	 * you will probably get a negative number.
	 * 
	 * @param dateOfBirth
	 * @param futureDate
	 * @return
	 */
    public static int computeAge(Date dateOfBirth, Date futureDate) {
        Calendar birthdayDay = Calendar.getInstance();
        birthdayDay.setTime(dateOfBirth);

        Calendar futureDay = Calendar.getInstance();
        futureDay.setTime(futureDate);

        int yearDifference =
            DateUtil.getYear(futureDate)
                - DateUtil.getYear(dateOfBirth);

        birthdayDay.roll(Calendar.YEAR, yearDifference);

        boolean notHadBirthdayYetThisYear =
            birthdayDay.after(futureDay);

        int yearsAlive =
            yearDifference - (notHadBirthdayYetThisYear ? 1 : 0);

        return yearsAlive;
    }

}
