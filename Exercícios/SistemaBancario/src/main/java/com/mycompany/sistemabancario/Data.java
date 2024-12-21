package com.mycompany.sistemabancario;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Data {

    private int day, month, year;
    Calendar cal = GregorianCalendar.getInstance();
    protected int currentYear = cal.get(Calendar.YEAR);

    /**
     *
     * @param day
     * @param month
     * @param year
     */
    public Data(int day, int month, int year) {
        if (day > 0 && day <= 31) {
            if (month > 0 && month <= 12) {
                if (year <= currentYear) {
                    this.day = day;
                    this.month = month;
                    this.year = year;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "\nData de aniversário: " + this.day + "/" + this.month + "/" + this.year;
    }
}
