package grapecity.fitnessexplorer.ui.views;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.DayActivities;
import java.util.ArrayList;
import grapecity.fitnessexplorer.R;
import grapecity.fitnessexplorer.factories.RandomColorFactory;
import grapecity.fitnessexplorer.util.DimensionUtil;

/**
 * Created by David.Bickford on 6/3/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private ArrayList<DayActivities> mDataset;
    private ArrayList<CalorieActivity> activities;
    private RandomColorFactory colorFactory;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View colorView;
        public View padding;
        public TextView title;
        public ViewHolder(View v)
        {
            super(v);
            colorView = v.findViewById(R.id.colorview);
            padding = v.findViewById(R.id.paddingview);
            title = (TextView)v.findViewById(R.id.textview);
            title.setPadding(0, DimensionUtil.getDimensionSize(5), 0, 0);
            title.setTypeface(null, Typeface.BOLD);
        }
    }

    public MyAdapter(ArrayList<DayActivities> myDataset)
    {
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_day_activity_list_item, parent, false);
        v.setPadding(DimensionUtil.getDimensionSize(10), DimensionUtil.getDimensionSize(10),
                DimensionUtil.getDimensionSize(10), DimensionUtil.getDimensionSize(10));

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        if(activities != null)
        {
            holder.colorView.setBackgroundColor(colorFactory.getNewColor(activities.get(position).getActivity()).getColor());
            holder.title.setText(activities.get(position).getActivity() + " (" + activities.get(position).getCalorie() + ")");
        }
        else
        {
            holder.colorView.setBackgroundColor(Color.TRANSPARENT);
            holder.title.setText("");
        }

    }

    @Override
    public int getItemCount()
    {
        if(activities == null)
        {
            return mDataset.size();
        }
        else
        {
            return activities.size();
        }
    }

    public void setDayActivities(ArrayList<CalorieActivity> activityList, RandomColorFactory colorList)
    {
        activities = activityList;
        colorFactory = colorList;
        super.notifyDataSetChanged();
    }
}
