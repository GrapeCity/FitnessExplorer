package grapecity.fitnessexplorer.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.fitnessexplorer.entities.CalorieDate;
import com.grapecity.xuni.chartcore.ChartPositionType;
import com.grapecity.xuni.chartcore.Palettes;
import com.grapecity.xuni.flexchart.ChartLabelPosition;
import com.grapecity.xuni.flexchart.ChartMarkerLines;
import com.grapecity.xuni.flexchart.ChartSeries;
import com.grapecity.xuni.flexchart.FlexChart;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David.Bickford on 5/26/2016.
 */
public class FlexChartDashboardView extends DashboardView
{
    private FlexChart mChart;

    public FlexChartDashboardView(Context context, ArrayList<CalorieDate> days)
    {
        super(context);
        init(context);
    }

    public FlexChartDashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlexChartDashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context)
    {
        super.title.setText("Calories burned this week");
        mChart = new FlexChart(context);
        mChart.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        //Set the binding for X-axis of FlexChart
        mChart.setBindingX("day");

        mChart.getAxisX().setMinorGridColor(Color.TRANSPARENT);
        mChart.getAxisX().setMajorGridColor(Color.TRANSPARENT);
        mChart.getAxisY().setMajorGridColor(Color.TRANSPARENT);
        mChart.getAxisY().setLabelFontColor(Color.TRANSPARENT);
        mChart.getAxisX().setMajorTickColor(Color.TRANSPARENT);
        mChart.getAxisX().setMinorTickColor(Color.TRANSPARENT);
        mChart.getAxisY().setMajorTickColor(Color.TRANSPARENT);
        mChart.getAxisX().setLineColor(Color.TRANSPARENT);
        mChart.getAxisY().setLineColor(Color.TRANSPARENT);
        mChart.getAxisX().setLabelFontColor(Color.BLACK);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setBorderColor(Color.WHITE);
        mChart.setPalette(Palettes.DARK);
        mChart.legend.setPosition(ChartPositionType.NONE);
        mChart.getDataLabel().setPosition(ChartLabelPosition.TOP);
        mChart.getMarker().setLines(ChartMarkerLines.NONE);
        mChart.setToggleLegend(false);
        mChart.setAnimated(false);

        super.contentLayout.addView(mChart);
    }

    public void setChart(List<CalorieDate> activitiesThisWeek)
    {
        ChartSeries seriesSales = new ChartSeries(mChart, "Calories lost", "calorie");
        mChart.setBindingX("day");
        // Add series to list
        mChart.getSeries().add(seriesSales);
        mChart.setItemsSource(activitiesThisWeek);
    }
}
