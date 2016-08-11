package grapecity.fitnessexplorer;


import android.test.AndroidTestCase;


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
        ActivityColor color = colorFactory.getNewColor("Lifting");
        ActivityColor color2 = colorFactory.getNewColor("Running");
        ActivityColor color3 = colorFactory.getNewColor("Walking");
        ActivityColor color4 = colorFactory.getNewColor("Lifting");
        assertEquals("Lifting", colorFactory.getNewColor("Lifting").getActivity());
    }
}
