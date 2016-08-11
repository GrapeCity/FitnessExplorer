package grapecity.fitnessexplorer.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import com.fitnessexplorer.entities.CalorieActivity;
import com.grapecity.xuni.chartcore.ChartPositionType;
import com.grapecity.xuni.chartcore.Palettes;
import com.grapecity.xuni.core.MarginF;
import com.grapecity.xuni.flexpie.FlexPie;
import com.grapecity.xuni.flexpie.PieLabelPosition;
import java.util.List;

/**
 * Created by David.Bickford on 5/26/2016.
 */
public class FlexPieDashboardView extends DashboardView
{
    private FlexPie mFlexPie;

    public FlexPieDashboardView(Context context)
    {
        super(context);
        init(context);
    }

    public FlexPieDashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlexPieDashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        super.title.setText("Today's activities");
        mFlexPie = new FlexPie(context);

        MarginF mPlotMargin = new MarginF(10f, 10f, 10f, 10f);
        mFlexPie.setPlotMargin(mPlotMargin);
        mFlexPie.setInnerRadius(0.65f);
        mFlexPie.legend.setPosition(ChartPositionType.BOTTOM);
        mFlexPie.legend.setBackgroundColor(Color.TRANSPARENT);
        mFlexPie.setPalette(Palettes.DARK);
        mFlexPie.getDataLabel().setPosition(PieLabelPosition.OUTSIDE);
        mFlexPie.setBackgroundColor(Color.WHITE);
        mFlexPie.setBorderColor(Color.WHITE);

        super.contentLayout.addView(mFlexPie);
    }

    public void setPie(List<CalorieActivity> calorieList)
    {
        mFlexPie.setBindingName("activity");
        mFlexPie.setBinding("calorie");
        mFlexPie.setItemsSource(calorieList);
    }
}
