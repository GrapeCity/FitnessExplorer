package com.fitnessexplorer.entities;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class DayActivities
{
    private Calendar startDate;
    private Calendar endDate;
    private int year;
    private int month;
    private int day;
    private ArrayList<CalorieActivity> activities;

    public DayActivities()
    {
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        activities = new ArrayList<CalorieActivity>();
    }

    public DayActivities(int y, int m, int d, ArrayList act)
    {
        startDate = Calendar.getInstance();
        year = y;
        month = m;
        day = d;
        startDate.set(year, month, day);
        activities = new ArrayList<CalorieActivity>();
        for(int i=0; i<act.size(); i++)
        {
            activities.add((CalorieActivity)act.get(i));
        }
    }

    public int getSize()
    {
        return activities.size();
    }

    public void setEndDate(Calendar endDate)
    {
        this.endDate = endDate;
    }

    public Calendar getEndDate()
    {
        return endDate;
    }

    public void setStartDate(Calendar startDate)
    {
        this.startDate = startDate;
    }

    public void setYear(int y)
    {
        year = y;
        startDate.set(year, month, day);
    }

    public void setMonth(int y)
    {
        month = y;
        startDate.set(year, month, day);
    }

    public void setDay(int y)
    {
        day = y;
        startDate.set(year, month, day);
    }

    public Calendar getStartDate()
    {
        return startDate;
    }

    public int getYear()
    {
        return year;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public void addActivity(CalorieActivity act)
    {
        activities.add(act);
    }

    public ArrayList getActivities()
    {
        return activities;
    }

    public CalorieActivity getSpecificActivity(int index)
    {
        return activities.get(index);
    }

    public void setSpecificActivity(int index, String act)
    {
        CalorieActivity currAct = activities.get(index);
        currAct.setActivity(act);
    }
}
