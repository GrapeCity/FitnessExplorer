package grapecity.fitnessexplorer.factories;

import java.util.HashMap;
import java.util.Random;
import grapecity.fitnessexplorer.entities.ActivityColor;
import android.graphics.Color;

import com.grapecity.xuni.chartcore.Palettes;
import com.grapecity.xuni.core.util.ColorUtil;

/**
 * Created by David.Bickford on 5/25/2016.
 */
public class RandomColorFactory
{
    private HashMap<String, Integer> colorMap = new HashMap<>();

    public Integer getNewColor(String activity)
    {
        if(colorMap.containsKey(activity))
        {
            return colorMap.get(activity);
        }
        else
        {
            int colorIndex = colorMap.size() % Palettes.DARK.length;

            int newColor = Palettes.DARK[colorIndex];

            int alphaMultiplier = (int) Math.ceil(colorMap.size() + 1 / Palettes.DARK.length);

            if(alphaMultiplier > 1)
            {
                alphaMultiplier--;

                newColor = ColorUtil.applyAlpha(newColor, 255 - (alphaMultiplier * 50));
            }

            colorMap.put(activity, newColor);

            return newColor;
        }
    }
}
