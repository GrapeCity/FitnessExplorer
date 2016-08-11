package com.fitnessexplorer.entities;

import java.util.Calendar;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class CalorieActivity extends Calorie
{
    private String activity;
    private int activityNumber;
    private Calendar startDate;
    private Calendar endDate;

    public CalorieActivity()
    {
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
    }

    public CalorieActivity(int cal, String act)
    {
        super(cal);
        activity = act;
    }

    public CalorieActivity(int cal, String activity, Calendar startDate, Calendar endDate)
    {
        super(cal);
        this.activity = activity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setActivityNumber(int activityNumber)
    {
        this.activityNumber = activityNumber;
    }

    public int getActivityNumber()
    {
        return activityNumber;
    }

    public void setStartDate(Calendar startDate)
    {
        this.startDate = startDate;
    }

    public Calendar getStartDate()
    {
        return startDate;
    }

    public void setEndDate(Calendar endDate)
    {
        this.endDate = endDate;
    }

    public Calendar getEndDate()
    {
        return endDate;
    }

    public void setActivity(String activity)
    {
        this.activity = activity;
    }

    public String getActivity()
    {
        return activity;
    }
}
