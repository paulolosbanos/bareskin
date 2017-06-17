package com.mybareskinph.theBareskinApp.util;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDate implements Serializable {
    public static final String YEAR_KEY = "year";
    public static final String MONTH_OF_YEAR_KEY = "monthOfYear";
    public static final String DAY_OF_MONTH_KEY = "dayOfMonth";
    private int year;
    private int monthOfYear;
    private int dayOfMonth;

    public CalendarDate(int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
    }

    public static CalendarDate fromBundle(@NonNull Bundle bundle) {
        return new CalendarDate(bundle.getInt(YEAR_KEY), bundle.getInt(MONTH_OF_YEAR_KEY), bundle.getInt(DAY_OF_MONTH_KEY));
    }

    public static CalendarDate fromString(String stringDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return CalendarDate.fromDate(format.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putInt(YEAR_KEY, getYear());
        result.putInt(MONTH_OF_YEAR_KEY, getMonthOfYear());
        result.putInt(DAY_OF_MONTH_KEY, getDayOfMonth());
        return result;
    }

    public Date toJavaDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getYear(), getMonthOfYear(), getDayOfMonth());
        return calendar.getTime();
    }

    public String toStringDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(toJavaDate());
    }

    public static CalendarDate fromDate(Date dateOfBirth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);
        return new CalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
}
