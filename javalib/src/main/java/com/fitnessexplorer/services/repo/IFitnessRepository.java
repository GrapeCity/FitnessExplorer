package com.fitnessexplorer.services.repo;

import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import java.util.Calendar;
import java.util.List;

/**
 * Created by David.Bickford on 5/23/2016.
 */
public interface IFitnessRepository
{
    void getCaloriesBurnedToday(Task<Calorie> onFinishedListener);

    void getCaloriesBurnedThisWeek(Task<List<CalorieDate>> onFinishedListener);

    void getCalorieActivitiesToday(Task<List<CalorieActivity>> onFinishedListener);

    void getMonthDayActivities(Task<List<DayActivities>> onFinishedListener);

    void loadActivityDataPointsAsync(Task<List<ActivityDataPoint>> onFinishedListener, Calendar startDate, Calendar endDate);

    List<ActivityDataPoint> loadActivityDataPoints(Calendar startDate, Calendar endDate);

    void subscribe(IRepositoryChangeListener listener);

    void unsubscribe(IRepositoryChangeListener listener);
}
