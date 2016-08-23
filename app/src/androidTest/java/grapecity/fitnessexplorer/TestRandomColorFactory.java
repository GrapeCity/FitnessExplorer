package grapecity.fitnessexplorer;


import android.graphics.Color;
import android.test.AndroidTestCase;


import com.grapecity.xuni.chartcore.Palettes;

import grapecity.fitnessexplorer.entities.ActivityColor;
import grapecity.fitnessexplorer.factories.RandomColorFactory;

/**
 * Created by David.Bickford on 5/25/2016.
 */

public class TestRandomColorFactory extends AndroidTestCase
{
    public void testColorFactory()
    {
        RandomColorFactory colorFactory = new RandomColorFactory();
        int color = colorFactory.getNewColor("Lifting");
        int color2 = colorFactory.getNewColor("Running");
        int color3 = colorFactory.getNewColor("Walking");
        int color4 = colorFactory.getNewColor("Lifting");

        assertTrue(color != color2 && color != color3 && color == color4);
        assertTrue(color2 != color3 && color2 != color4);
        assertTrue(color3 != color4);
    }

    public void testColorFactoryAlpha()
    {
        RandomColorFactory colorFactory = new RandomColorFactory();

        int paletteSize = Palettes.DARK.length;

        for(int i = 0; i < paletteSize; i++)
        {
            colorFactory.getNewColor("BlahNoAlpha" + i);
        }

        for(int i = 0; i < paletteSize; i++)
        {
            colorFactory.getNewColor("BlahWithAlpha" + i);
        }

        int firstColorNoAlpha = colorFactory.getNewColor("BlahNoAlpha0");
        int firstColorWithAlpha = colorFactory.getNewColor("BlahWithAlpha0");

        assertEquals(Color.blue(firstColorNoAlpha), Color.blue(firstColorWithAlpha));
        assertEquals(Color.red(firstColorNoAlpha), Color.red(firstColorWithAlpha));
        assertEquals(Color.green(firstColorNoAlpha), Color.green(firstColorWithAlpha));
        assertEquals(255, Color.alpha(firstColorNoAlpha));
        assertEquals(111, Color.alpha(firstColorWithAlpha));
    }
}
