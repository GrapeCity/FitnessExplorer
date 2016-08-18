package grapecity.fitnessexplorer.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.DayActivities;
import com.grapecity.xuni.calendar.CalendarDaySlotBase;
import com.grapecity.xuni.calendar.CalendarDaySlotLoadingEventArgs;
import com.grapecity.xuni.calendar.CalendarOrientation;
import com.grapecity.xuni.calendar.CalendarSelectionChangingEventArgs;
import com.grapecity.xuni.calendar.XuniCalendar;
import com.grapecity.xuni.core.IEventHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import grapecity.fitnessexplorer.R;
import grapecity.fitnessexplorer.factories.RandomColorFactory;
import grapecity.fitnessexplorer.util.DimensionUtil;

/**
 * Created by David.Bickford on 5/26/2016.
 */
public class CalendarDashboardView extends DashboardView
{
    private XuniCalendar calendar;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private ArrayList<DayActivities> monthActivities = new ArrayList<>();
    private Context context;

    public CalendarDashboardView(Context context)
    {
        super(context);
        init(context);
    }

    public CalendarDashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarDashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context)
    {
        this.context = context;

        calendar = new XuniCalendar(context);
        super.contentLayout.addView(calendar);
        calendar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, DimensionUtil.getDimensionSize(300)));

        calendar.setBackgroundColor(Color.WHITE);
        calendar.setDayBorderColor(Color.BLACK);
        calendar.setTextColor(Color.BLACK);
        calendar.setOrientation(CalendarOrientation.Vertical);
        calendar.setDayOfWeekBackgroundColor(Color.LTGRAY);
        calendar.setSelectionBackgroundColor(Color.LTGRAY);
        calendar.setShowNavigationButtons(false);
        calendar.setShowHeader(false);

        Calendar now = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(now.getTime());
        super.title.setText(String.format(getResources().getString(R.string.activities_month), month));
    }

    public void setCalendar(List<DayActivities> activitiesThisMonth)
    {
        monthActivities = new ArrayList<>();
        monthActivities.addAll(activitiesThisMonth);

        calendar.getDaySlotLoading().addHandler(new IEventHandler()
        {
            @Override
            public void call(Object arg0, Object arg1)
            {
                CalendarDaySlotLoadingEventArgs args = (CalendarDaySlotLoadingEventArgs) arg1;
                CalendarDaySlotBase layout = new CalendarDaySlotBase(context);

                LinearLayout mainLayout = new LinearLayout(context);
                mainLayout.setOrientation(LinearLayout.VERTICAL);
                TextView date = new TextView(context);
                Calendar cal = Calendar.getInstance();
                Date daysDate = args.getDate();
                cal.setTime(daysDate);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                date.setText(Integer.toString(day));
                date.setGravity(Gravity.CENTER);

                if(!args.isAdjacentDay())
                {
                    date.setTextColor(Color.BLACK);
                }
                else
                {
                    date.setTextColor(Color.LTGRAY);
                }
                mainLayout.addView(date);

                LinearLayout row1 = new LinearLayout(context);
                LinearLayout row2 = new LinearLayout(context);
                int viewNum = 0;
                int boxDimension = 5;
                row1.setOrientation(LinearLayout.HORIZONTAL);
                row2.setOrientation(LinearLayout.HORIZONTAL);

                for(int i=0; i<monthActivities.size(); i++)
                {
                    DayActivities currDay = monthActivities.get(i);
                    if(currDay.getStartDate().get(Calendar.DAY_OF_MONTH) == day)
                    {
                        for(int j=0; j<currDay.getActivities().size(); j++)
                        {
                            CalorieActivity currActivity = (CalorieActivity)currDay.getActivities().get(j);
                            View square = new View(context);
                            square.setBackgroundColor(RandomColorFactory.getNewColor(currActivity.getActivity()).getColor());
                            square.setLayoutParams(new LayoutParams(DimensionUtil.getDimensionSize(boxDimension), DimensionUtil.getDimensionSize(boxDimension)));
                            square.invalidate();
                            View padding = new View(context);
                            padding.setBackgroundColor(Color.TRANSPARENT);
                            padding.setLayoutParams(new LayoutParams(DimensionUtil.getDimensionSize(5), DimensionUtil.getDimensionSize(boxDimension)));
                            padding.invalidate();
                            if(viewNum < 3)
                            {
                                row1.addView(square);
                                row1.addView(padding);
                            }
                            else if(viewNum >= 3 && viewNum < 6)
                            {
                                row2.addView(square);
                                row2.addView(padding);
                            }
                            viewNum++;
                        }
                    }
                }
                row1.setLayoutParams(new LayoutParams(DimensionUtil.getDimensionSize(boxDimension*5), DimensionUtil.getDimensionSize(boxDimension)));
                LinearLayout.LayoutParams paramsRow1 = new LinearLayout.LayoutParams(row1.getLayoutParams());
                paramsRow1.setMargins(0, 0, 0, DimensionUtil.getDimensionSize(5));
                row1.setLayoutParams(paramsRow1);

                mainLayout.addView(row1);
                mainLayout.addView(row2);
                layout.addView(mainLayout);
                args.setDaySlot(layout);
            }
        }, this);

        recyclerView = new RecyclerView(context);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
        mAdapter = new MyAdapter(monthActivities);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DimensionUtil.getDimensionSize(50)));
        recyclerView.setBackgroundColor(Color.WHITE);

        calendar.getSelectionChanging().addHandler(new IEventHandler()
        {
            @Override
            public void call(Object arg0, Object arg1)
            {
                CalendarSelectionChangingEventArgs arg = (CalendarSelectionChangingEventArgs) arg1;
                List<Date> days = arg.getSelectedDates();
                Calendar cal = Calendar.getInstance();
                cal.setTime(days.get(0));

                for(int i=0; i<monthActivities.size(); i++)
                {
                    DayActivities currDay = monthActivities.get(i);
                    if(currDay.getStartDate().get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH))
                    {
                        mAdapter.setDayActivities(currDay.getActivities());
                        break;
                    }
                    else
                    {
                        mAdapter.setDayActivities(null);
                    }
                }
            }
        }, this);
        super.contentLayout.addView(recyclerView);
        calendar.setSelectedDate(GregorianCalendar.getInstance().getTime());
    }
}
