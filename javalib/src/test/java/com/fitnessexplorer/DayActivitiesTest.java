package com.fitnessexplorer;

import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.DayActivities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class DayActivitiesTest
{
    @Test
    public void testConstructor()
    {
        ArrayList<CalorieActivity> list = new ArrayList<>();
        list.add(new CalorieActivity(100, "Swimming"));
        list.add(new CalorieActivity(200, "Running"));
        list.add(new CalorieActivity(150, "Walking"));
        DayActivities dayActivities = new DayActivities(2016, 5, 23, list);

        //passes or fails test
        assertEquals(2016, dayActivities.getYear());
        assertEquals(5, dayActivities.getMonth());
        assertEquals(23, dayActivities.getDay());
        assertEquals(list, dayActivities.getActivities());
        assertEquals("Swimming", dayActivities.getSpecificActivity(0).getActivity());
        assertEquals("Running", dayActivities.getSpecificActivity(1).getActivity());
        assertEquals("Walking", dayActivities.getSpecificActivity(2).getActivity());
    }

    @Test
    public void testSetter()
    {
        ArrayList<CalorieActivity> list = new ArrayList<>();
        list.add(new CalorieActivity(100, "Swimming"));
        list.add(new CalorieActivity(200, "Running"));
        list.add(new CalorieActivity(150, "Walking"));
        DayActivities dayActivities = new DayActivities(2016, 5, 23, list);
        //testing DayActivities Calendar
        dayActivities.setYear(2015);
        assertEquals(2015, dayActivities.getYear());
        dayActivities.setMonth(6);
        assertEquals(6, dayActivities.getMonth());
        dayActivities.setDay(25);
        assertEquals(25, dayActivities.getDay());
        Calendar today = Calendar.getInstance();
        Date date = new Date();
        today.setTime(date);
        dayActivities.setStartDate(today);
        assertEquals(2015, dayActivities.getYear());
        assertEquals(6, dayActivities.getMonth());
        assertEquals(25, dayActivities.getDay());

        //testing DayActivities ArrayList
        assertEquals(3, dayActivities.getSize());
        dayActivities.setSpecificActivity(0, "Pole Vaulting");
        assertEquals("Pole Vaulting", dayActivities.getSpecificActivity(0).getActivity());
        dayActivities.setSpecificActivity(1, "Lifting");
        assertEquals("Lifting", dayActivities.getSpecificActivity(1).getActivity());
        dayActivities.setSpecificActivity(2, "Cycling");
        assertEquals("Cycling", dayActivities.getSpecificActivity(2).getActivity());
    }

}
