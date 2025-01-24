package com.mycompany.sistemabancario;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Data {

    private int day, month, year;
    private final int currentYear;

    public Data() {
        Calendar cal = GregorianCalendar.getInstance();
        currentYear = cal.get(Calendar.YEAR);
    }

    public Data(int day, int month, int year) {
        this();
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Data inválida!");
        }
    }

    private boolean isValidDate(int day, int month, int year) {
        if (year > currentYear || month < 1 || month > 12 || day < 1) {
            return false;
        }
        int maxDays = getDaysInMonth(month, year);
        return day <= maxDays;
    }

    private int getDaysInMonth(int month, int year) {
        return switch (month) {
            case 2 ->
                (isLeapYear(year) ? 29 : 28);
            case 4, 6, 9, 11 ->
                30;
            default ->
                31;
        };
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (isValidDate(day, this.month, this.year)) {
            this.day = day;
        } else {
            throw new IllegalArgumentException("\nDia inválido!\n");
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (isValidDate(this.day, month, this.year)) {
            this.month = month;
        } else {
            throw new IllegalArgumentException("\nMês inválido!\n");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (isValidDate(this.day, this.month, year)) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("\nAno inválido!\n");
        }
    }

    public String getFormattedDate() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    @Override
    public String toString() {
        return "\nData de aniversário: " + getFormattedDate();
    }
}
