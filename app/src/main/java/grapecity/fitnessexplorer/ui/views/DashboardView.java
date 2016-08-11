package grapecity.fitnessexplorer.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import grapecity.fitnessexplorer.util.DimensionUtil;

/**
 * Created by David.Bickford on 5/26/2016.
 */
public class DashboardView extends CardView
{
    public LinearLayout contentLayout;
    public TextView title;

    public DashboardView(Context context)
    {
        super(context);
        init(context);
    }

    public DashboardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public DashboardView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        contentLayout = new LinearLayout(context);
        contentLayout.setGravity(Gravity.CENTER);
        title = new TextView(context);
        contentLayout.addView(title);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setPadding(DimensionUtil.getDimensionSize(16), DimensionUtil.getDimensionSize(16), DimensionUtil.getDimensionSize(24), DimensionUtil.getDimensionSize(16));
        title.setGravity(Gravity.CENTER);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        title.setTypeface(null, Typeface.BOLD);
        this.addView(contentLayout);
    }
}