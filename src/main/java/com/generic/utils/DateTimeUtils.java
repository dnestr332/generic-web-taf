package com.generic.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

    private static final DateTimeFormatter TIME_FORMAT_HHMM = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_MDYYYY = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter DATE_FORMAT_MMDDYYYY = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter TIME_FORMAT_ALT = DateTimeFormatter.ofPattern("h:mm a");
    private static final DateTimeFormatter DATE_FORMAT_YYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_YYYYMMDD_HHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateTimeUtils() {}

    public static String getCurrentTime() {
        return OffsetDateTime.now()
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static LocalDateTime timePlusMinutes(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes);
    }

    public static LocalDateTime timePlusHours(int hours) {
        return LocalDateTime.now().plusHours(hours);
    }

    public static String getTimePlusHours(int hours) {
        return timePlusHours(hours).format(TIME_FORMAT_HHMM);
    }

    public static String getCurrentDate() {
        return timePlusHours(0).format(DATE_FORMAT_MMDDYYYY);
    }

    public static String datePlusDays(int days) {
        return LocalDateTime.now().plusDays(days).format(DATE_FORMAT_MMDDYYYY);
    }

    public static String formatPickupAt(LocalDateTime time) {
        return time.format(DATE_TIME_YYYYMMDD_HHMMSS);
    }

    public static String formatPickupDate(LocalDateTime time) {
        return time.format(DATE_FORMAT_MDYYYY);
    }

    public static String formatTodayDate(LocalDateTime time) {
        return time.format(DATE_FORMAT_MMDDYYYY);
    }

    public static String formatPickupTime(LocalDateTime time) {
        return time.format(TIME_FORMAT_HHMM);
    }

    public static String formatDbPickupDate(LocalDateTime time) {
        return time.format(DATE_FORMAT_YYYYMMDD);
    }

    public static String currentTimePlusMinutes(int minutes) {
        return LocalTime.now()
                .plusMinutes(minutes)
                .format(TIME_FORMAT_ALT);
    }
}