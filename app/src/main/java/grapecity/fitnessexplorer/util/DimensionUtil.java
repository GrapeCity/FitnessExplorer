package grapecity.fitnessexplorer.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by David.Bickford on 6/2/2016.
 */
public class DimensionUtil
{
    public static int getDimensionSize(int size)
    {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, Resources.getSystem().getDisplayMetrics()));
    }
}
