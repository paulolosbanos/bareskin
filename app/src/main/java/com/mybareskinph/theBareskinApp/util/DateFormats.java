package com.mybareskinph.theBareskinApp.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormats {

    private static final int ONE_DAY = 24 * 60 * 60 * 1000;
    public static final java.text.DateFormat DATE_FORMAT_EMDY = new SimpleDateFormat("EEE, dd MMM yy");
    public static final java.text.DateFormat DATE_FORMAT_EMDYYYY = new SimpleDateFormat("EEE, dd MMM yyyy");
    public static final java.text.DateFormat DATE_FORMAT_EDM = new SimpleDateFormat("EEEE, dd MMMM yyyy");
    public static final java.text.DateFormat TIME_FORMAT_HMA = new SimpleDateFormat("hh:mm a");
    public static final java.text.DateFormat TIME_FORMAT_MILITARY = new SimpleDateFormat("HH:mm");

    public static final String FORMAT_12_HOUR = "h:mm a";
    public static final String FORMAT_24_HOUR = "HH:mm";

    public static String formatTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return dateFormat.format(date);
    }

    @NonNull
    public static String formatDateTime(@NonNull final Context context, Date date) {
        return DateFormat.getDateFormat(context).format(date) + " " + DateFormat.getTimeFormat(context).format(date);
    }

    public static String formatTime(long ts) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ts);
        return formatTime(calendar.getTime());
    }

    @NonNull
    public static java.text.DateFormat buildIso8601Format() {
        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return iso8601Format;
    }

    @NonNull
    public static String getYear(@NonNull final Date date) {
        return new SimpleDateFormat("yyyy", Locale.getDefault()).format(date);
    }

    @NonNull
    public static String getMonth(@NonNull final Date date) {
        return new SimpleDateFormat("MMMM", Locale.getDefault()).format(date);
    }

    /**
     * Return date in "Sat, Mar 01" format
     */
    @NonNull
    public static String getWeekdayDate(@NonNull final Date date) {
        return new SimpleDateFormat("EEE, MMM dd", Locale.getDefault()).format(date);
    }

    @NonNull
    private static SimpleDateFormat getDayOfWeekFormat() {
        return new SimpleDateFormat("EEEE", Locale.getDefault()); //EEEE -> Monday
    }

    /**
     * "21 Mar 2017"
     */
    public static String formatDayMonthYear(@NonNull final Date date) {
        return new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date);
    }

    public static String formatDayMonthYearRange(@NonNull final Date dateFrom,
                                                 @NonNull final Date dateTo) {
        return String.format("%s - %s", formatDayMonthYear(dateFrom), formatDayMonthYear(dateTo));
    }

    /**
     * "Thursday, 21 Mar 2017"
     */
    public static String formatDayNameDayMonthYear(@NonNull final Date date) {
        return new SimpleDateFormat("EEEE, d MMM yyyy", Locale.getDefault()).format(date);
    }

    /**
     * Get instance without years
     */
    private static java.text.DateFormat getDayMonthInstanceWithoutYears() {
        return new SimpleDateFormat("d MMM", Locale.getDefault());
    }

    /**
     * "Friday, 5 Mar 3:42 PM"-like format
     */
    public static String formatDateTime(long ts) {
        return getDayOfWeekFormat().format(ts) +
                ", "
                + getDayMonthInstanceWithoutYears().format(ts) +
                " "
                + java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT, Locale.getDefault()).format(ts);
    }

    /**
     * Fri, 5 Mar 17 9:04 AM
     */
    public static String formatDayDateTime(final long time) {
        return new SimpleDateFormat("EEE, d, MMM yy H:mm", Locale.getDefault()).format(new Date(time));
    }

    @Nullable
    public static String formatDateForAce(@Nullable final Date date) {
        if (date == null) {
            return null;
        } else {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getAceDateFormat(), Locale.US);
            return simpleDateFormat.format(date);
        }
    }

    @NonNull
    public static String getAceDateFormat() {
        return "yyyy-MM-dd";
    }

    @Nullable
    public static Date parseDateWithFormat(@Nullable final String stringWithDate, @Nullable final String dateFormat) {
        if (stringWithDate == null || dateFormat == null) {
            return null;
        }

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        try {
            return simpleDateFormat.parse(stringWithDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static java.text.DateFormat buildReceiptFormat() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    }

    public static String fromMilliToHHMMSS(long totalTimeInMillisecond) {
        String formattedTimeStamp;
        int seconds = (int) (totalTimeInMillisecond / 1000) % 60;
        int minutes = (int) ((totalTimeInMillisecond / (1000 * 60)) % 60);
        int hours = (int) ((totalTimeInMillisecond / (1000 * 60 * 60)) % 24);
        formattedTimeStamp = String.format(Locale.getDefault(), "%02d:%02d:%02d left", hours, minutes, seconds);
        return formattedTimeStamp;
    }
}
