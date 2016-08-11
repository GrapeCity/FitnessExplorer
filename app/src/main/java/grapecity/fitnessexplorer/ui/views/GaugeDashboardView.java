package grapecity.fitnessexplorer.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import com.fitnessexplorer.entities.Calorie;
import com.grapecity.xuni.gauge.GaugeRange;
import com.grapecity.xuni.gauge.XuniRadialGauge;
import grapecity.fitnessexplorer.ui.base.FitNotConnectedFragment;

/**
 * Created by David.Bickford on 5/26/2016.
 */
public class GaugeDashboardView extends DashboardView
{
    private XuniRadialGauge gauge;
    public FitNotConnectedFragment fragment;

    public GaugeDashboardView(Context context, Calorie calories)
    {
        super(context);
        init(context, calories);
    }

    public GaugeDashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, new Calorie());
    }

    public GaugeDashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, new Calorie());
    }

    private void init(Context context, Calorie calories)
    {
        super.title.setText("Calories burned today");
        gauge = new XuniRadialGauge(context);
        fragment = new FitNotConnectedFragment();

        gauge.setReadOnly(true);
        gauge.setStartAngle(0);
        gauge.setSweepAngle(180);
        gauge.setAutoScale(true);
        gauge.setGaugeWidth(.4f);
        gauge.setBackgroundColor(Color.WHITE);
        gauge.setMinFontColor(Color.GRAY);
        gauge.setMaxFontColor(Color.GRAY);
        gauge.setValueFontColor(Color.BLACK);
        gauge.setValueFontSize(30);

        if(gauge.getValue() != calories.getCalorie())
        {
            gauge.refresh();
        }

        super.contentLayout.addView(gauge);
    }

    public void setCalories(Calorie calorie)
    {
        int maxCalories = 1000;
        int offset;
        offset = calorie.getCalorie() % 1000;
        maxCalories += calorie.getCalorie() - offset;
        gauge.setValue(calorie.getCalorie());

        GaugeRange range = new GaugeRange();
        range.setMax(maxCalories);
        range.setMin(0);

        range.setBorderColor(Color.GRAY);
        gauge.setFace(range);
    }
}
