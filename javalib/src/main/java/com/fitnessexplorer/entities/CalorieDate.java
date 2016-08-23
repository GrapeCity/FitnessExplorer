package com.fitnessexplorer.entities;

import java.util.Calendar;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class CalorieDate extends Calorie
{
    private String day;

    public CalorieDate(int calorie, String day)
    {
        super(calorie);

        this.day = day;
    }

    public String getDay()
    {
        return day;
    }
}
