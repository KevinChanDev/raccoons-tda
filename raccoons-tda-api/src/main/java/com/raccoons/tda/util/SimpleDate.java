package com.raccoons.tda.util;

import java.time.LocalDate;

public class SimpleDate {

    private int year;
    private int month;
    private int dayOfMonth;

    public SimpleDate(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public static SimpleDate of(int year, int month, int dayOfMonth) {
        return new SimpleDate(year, month, dayOfMonth);
    }

    public static SimpleDate offsetDays(long days) {
        final LocalDate now = LocalDate.now();
        LocalDate modified;
        if (days > 0) {
            modified = now.plusDays(days);
        } else if (days < 0) {
            modified = now.minusDays(Math.abs(days));
        } else {
            modified = now;
        }
        return SimpleDate.of(modified.getYear(), modified.getMonthValue(), modified.getDayOfMonth());
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}
