package com.fitnessexplorer;

import com.fitnessexplorer.entities.CalorieActivity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public class CalorieActivityTest
{

    @Test
    public void testConstructor()
    {
        CalorieActivity calorieActivity = new CalorieActivity(100, "Swimming");

        //passes or fails test
        assertEquals(100, calorieActivity.getCalorie());
        assertEquals("Swimming", calorieActivity.getActivity());
    }

    @Test
    public void testSetter()
    {
        CalorieActivity calorieActivity = new CalorieActivity(100, "Swimming");
        calorieActivity.setActivity("Running");
        assertEquals("Running", calorieActivity.getActivity());
    }

}
