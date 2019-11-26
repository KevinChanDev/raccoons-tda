package com.raccoons.tda.util;

public class SimpleDateTime extends SimpleDate {

    private int hour;
    private int minute;
    private int second;
    private int millisSecond;

    public SimpleDateTime(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public SimpleDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second, int millisSecond) {
        super(year, month, dayOfMonth);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisSecond = millisSecond;
    }

    public static SimpleDateTime of(int year, int month, int dayOfMonth) {
        return new SimpleDateTime(year, month, dayOfMonth);
    }

    public static SimpleDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int millisSecond) {
        return new SimpleDateTime(year, month, dayOfMonth, hour, minute, second, millisSecond);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMillisSecond() {
        return millisSecond;
    }
}
