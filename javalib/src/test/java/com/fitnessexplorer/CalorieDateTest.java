package com.fitnessexplorer;

import com.fitnessexplorer.entities.CalorieDate;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
/**
 * Created by David.Bickford on 5/23/2016.
 */

    /**
     * Created by David.Bickford on 5/23/2016.
     */
public class CalorieDateTest
{
    @Test
    public void testConstructor()
    {
        CalorieDate calorieDate = new CalorieDate(100, "Mon");

        //passes or fails test
        assertEquals(100, calorieDate.getCalorie());
        assertEquals("Mon", calorieDate.getDay());
    }
}
