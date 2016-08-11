package com.fitnessexplorer.ui.dashboard;

import com.fitnessexplorer.ui.base.IView;
import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import java.util.List;

/**
 * Created by David.Bickford on 5/24/2016.
 */
public interface IDashboardView extends IView
{
    void caloriesBurnedTodayLoaded(Calorie cal);

    void activitiesForTodayLoaded(List<CalorieActivity> activities);

    void caloriesBurnedThisWeekLoaded(List<CalorieDate> activitiesThisWeek);

    void activitiesThisMonthLoaded(List<DayActivities> activitiesThisMonth);

    void showFitNotConnectedDialog();

    void showData();

    void showNoData();

    void showConnecting();
}
