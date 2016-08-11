package com.fitnessexplorer;
/**
 * Created by David.Bickford on 5/23/2016.
 */
import com.fitnessexplorer.entities.Calorie;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalorieTest
{

    @Test
    public void testConstructor()
    {
        Calorie calorie = new Calorie(100);

        //passes or fails test
        assertEquals(100, calorie.getCalorie());
    }

    @Test
    public void testSetter()
    {
        Calorie calorie = new Calorie(100);
        calorie.setCalorie(125);
        assertEquals(125, calorie.getCalorie());
    }
}
