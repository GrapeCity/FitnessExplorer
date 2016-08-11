package com.fitnessexplorer;

import com.fitnessexplorer.entities.ActivityDataPoint;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class ActivityDataPointTest
{
    @Test
    public void testConstructor()
    {
        ActivityDataPoint activityDataPoint = new ActivityDataPoint("Lifting", 350, "Going to the gym to lift!",
                2016, 5, 23, 6, 30, 2017, 6, 24, 7, 30);

        //passes or fails test
        assertEquals(2016, activityDataPoint.getStartYear());
        assertEquals(5, activityDataPoint.getStartMonth());
        assertEquals(23, activityDataPoint.getStartDay());
        assertEquals(2017, activityDataPoint.getStartYear());
        assertEquals(6, activityDataPoint.getStartMonth());
        assertEquals(24, activityDataPoint.getStartDay());
        assertEquals("Swimming", activityDataPoint.getActivity());
        assertEquals("Going to the gym to lift!", activityDataPoint.getActivity());
    }

    @Test
    public void testSetter()
    {
        ActivityDataPoint activityDataPoint = new ActivityDataPoint("Lifting", 350, "Going to the gym to lift!",
                2016, 5, 23, 6, 30, 2017, 6, 24, 7, 30);
        //testing ActivityDataPoint startDate Calendar
        activityDataPoint.setStartYear(2016);
        assertEquals(2016, activityDataPoint.getStartYear());
        activityDataPoint.setStartMonth(5);
        assertEquals(5, activityDataPoint.getStartMonth());
        activityDataPoint.setStartDay(23);
        assertEquals(23, activityDataPoint.getStartDay());
        activityDataPoint.setStartDate(2015, 6, 25);
        assertEquals(2015, activityDataPoint.getStartYear());
        assertEquals(6, activityDataPoint.getStartMonth());
        assertEquals(25, activityDataPoint.getStartDay());

        //testing ActivityDataPoint endDate Calendar
        activityDataPoint.setEndYear(2017);
        assertEquals(2017, activityDataPoint.getEndYear());
        activityDataPoint.setEndMonth(6);
        assertEquals(6, activityDataPoint.getEndMonth());
        activityDataPoint.setEndDay(24);
        assertEquals(24, activityDataPoint.getEndDay());
        activityDataPoint.setEndDate(2015, 7, 25);
        assertEquals(2015, activityDataPoint.getEndYear());
        assertEquals(7, activityDataPoint.getEndMonth());
        assertEquals(25, activityDataPoint.getEndDay());

        //testing ActivityDataPoint activities and description
        activityDataPoint.setActivity("Running");
        assertEquals("Running", activityDataPoint.getActivity());
        activityDataPoint.setCalories(250);
        assertEquals(250, activityDataPoint.getCalories());
        activityDataPoint.setActivityDescription("I go run and stuffz");
        assertEquals("I go run and stuffz", activityDataPoint.getActivityDescription());
    }


}
