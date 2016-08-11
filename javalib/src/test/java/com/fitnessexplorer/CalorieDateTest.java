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
        CalorieDate calorieDate = new CalorieDate(100, 2016, 5, 23);

        //passes or fails test
        assertEquals(100, calorieDate.getCalorie());
        assertEquals(2016, calorieDate.getYear());
        assertEquals(5, calorieDate.getMonth());
        assertEquals(23, calorieDate.getDay());
    }

    @Test
    public void testSetter()
    {
        CalorieDate calorieDate = new CalorieDate(100, 2016, 5, 23);
        calorieDate.setCalorie(125);
        assertEquals(125, calorieDate.getCalorie());
        calorieDate.setYear(2015);
        assertEquals(2015, calorieDate.getYear());
        calorieDate.setMonth(6);
        assertEquals(6, calorieDate.getMonth());
        calorieDate.setDay(25);
        assertEquals(25, calorieDate.getDay());
        Calendar today = Calendar.getInstance();
        Date date = new Date();
        today.setTime(date);
        calorieDate.setCalendar(today);
        assertEquals(2015, calorieDate.getYear());
        assertEquals(6, calorieDate.getMonth());
        assertEquals(25, calorieDate.getDay());
    }

}
