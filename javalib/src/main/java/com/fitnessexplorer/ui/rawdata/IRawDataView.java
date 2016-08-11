package com.fitnessexplorer.ui.rawdata;

import com.fitnessexplorer.ui.base.IView;
import com.fitnessexplorer.entities.ActivityDataPoint;
import java.util.ArrayList;

/**
 * Created by David.Bickford on 5/24/2016.
 */
public interface IRawDataView extends IView
{
    void activityDataPointsForTheWeekLoaded(ArrayList<ActivityDataPoint> loadActivityDataPoints);

    void showData();

    void showConnecting();
}
