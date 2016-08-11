package com.fitnessexplorer.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class ActivityDataPoint
{
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("M/dd/yyyy");
    private String activity;
    private int calories;
    private String activityDescription;
    private Calendar startDate;
    private int startYear;
    private int startMonth;
    private int startDay;
    private int startHour;
    private int startMinute;
    private Calendar endDate;
    private int endYear;
    private int endMonth;
    private int endDay;
    private int endHour;
    private int endMinute;
    private String date;
    private int activityNumber;

    public ActivityDataPoint()
    {
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
    }

    public ActivityDataPoint(String act, int cal, String actDesc, int sY, int sM, int sD, int sH,
                             int sMin, int eY, int eM, int eD, int eH, int eMin)
    {
        activity = act;
        calories = cal;
        activityDescription = actDesc;

        startYear = sY;
        startMonth = sM;
        startDay = sD;
        startHour = sH;
        startMinute = sMin;
        startDate = Calendar.getInstance();
        startDate.set(startYear, startMonth-1, startDay, startHour, startMinute);

        endYear = eY;
        endMonth = eM;
        endDay = eD;
        endHour = eH;
        endMinute = eMin;
        endDate = Calendar.getInstance();
        endDate.set(endYear, endMonth-1, endDay, endHour, endMinute);

        date = DATE_FORMAT.format(startDate.getTime());
    }

    public void setActivityNumber(int activityNumber)
    {
        this.activityNumber = activityNumber;
    }

    public int getActivityNumber()
    {
        return activityNumber;
    }

    public void setStartDate(Calendar calendar)
    {
        this.startDate = calendar;
        date = DATE_FORMAT.format(startDate.getTime());
    }

    public void setEndDate(Calendar calendar)
    {
        this.endDate = calendar;
    }

    public void setActivity(String act)
    {
        activity = act;
    }

    public String getActivity()
    {
        return activity;
    }

    public void setCalories(int cal)
    {
        calories = cal;
    }

    public int getCalories()
    {
        return calories;
    }

    public void setActivityDescription(String desc)
    {
        activityDescription = desc;
    }

    public String getActivityDescription()
    {
        return activityDescription;
    }

    public void setStartDate(int y, int m, int d)
    {
        startYear = y;
        startMonth = m;
        startDay = d;
        startDate.set(startYear, startMonth, startDay);
    }

    public void setStartYear(int y)
    {
        startYear = y;
        startDate.set(startYear, startMonth, startDay);
    }

    public void setStartMonth(int y)
    {
        startMonth = y;
        startDate.set(startYear, startMonth, startDay);
    }

    public void setStartDay(int y)
    {
        startDay = y;
        startDate.set(startYear, startMonth, startDay);
    }

    public Calendar getStartDate()
    {
        return startDate;
    }

    public int getStartYear()
    {
        return startYear;
    }

    public int getStartMonth()
    {
        return startMonth;
    }

    public int getStartDay()
    {
        return startDay;
    }

    public void setEndDate(int y, int m, int d)
    {
        endYear = y;
        endMonth = m;
        endDay = d;
        endDate.set(endYear, endMonth, endDay);
    }

    public void setEndYear(int y)
    {
        endYear = y;
        endDate.set(endYear, endMonth, endDay);
    }

    public void setEndMonth(int y)
    {
        endMonth = y;
        endDate.set(endYear, endMonth, endDay);
    }

    public void setEndDay(int y)
    {
        endDay = y;
        endDate.set(endYear, endMonth, endDay);
    }

    public Calendar getEndDate()
    {
        return endDate;
    }

    public int getEndYear()
    {
        return endYear;
    }

    public int getEndMonth()
    {
        return endMonth;
    }

    public int getEndDay()
    {
        return endDay;
    }
}
