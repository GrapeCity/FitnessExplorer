package grapecity.fitnessexplorer.factories;

import java.util.HashMap;
import java.util.Random;
import grapecity.fitnessexplorer.entities.ActivityColor;
import android.graphics.Color;

/**
 * Created by David.Bickford on 5/25/2016.
 */
public class RandomColorFactory
{
    private static HashMap<String, ActivityColor> colorMap = new HashMap<>();

    public static ActivityColor getNewColor(String activity)
    {
        Random rand = new Random();
        int red, green, blue;

        if(colorMap.containsKey(activity))
        {
            return colorMap.get(activity);
        }
        else
        {
            red = rand.nextInt(256);
            green = rand.nextInt(256);
            blue = rand.nextInt(256);
            ActivityColor newColor = new ActivityColor(activity, Color.rgb(red, green, blue));
            colorMap.put(activity, newColor);
            return newColor;
        }
    }
}
