package grapecity.fitnessexplorer;

/**
 * Created by David.Bickford on 8/10/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.IRepositoryChangeListener;
import com.fitnessexplorer.services.repo.RepositoryState;
import com.fitnessexplorer.services.repo.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by David.Bickford on 5/23/2016.
 */
public class MemoryFitnessRepository implements IFitnessRepository
{
    private static boolean isThereData = true;

    private List<IRepositoryChangeListener> stateListeners = new ArrayList<>();
    private RepositoryState currentState = RepositoryState.CONNECTING;
    private Task<Calorie> caloriesBurnedTodayListener = null;
    private Task<List<CalorieDate>> caloriesBurnedThisWeekListener = null;
    private Task<List<CalorieActivity>> todayActivitiesListener = null;
    private Task<List<DayActivities>> monthDayActivitiesListener = null;
    private Task<List<ActivityDataPoint>> loadActivityDataPointsListener = null;

    private boolean connected = false;
    private Calendar startDate;
    private Calendar endDate;

    @Override
    public void getCaloriesBurnedToday(Task<Calorie> onFinishedListener)
    {
        if(connected)
        {
            onFinishedListener.onFinished(new Calorie(300));
        }
        else
        {
            caloriesBurnedTodayListener = onFinishedListener;
        }
    }

    @Override
    public void getCaloriesBurnedThisWeek(Task<List<CalorieDate>> onFinishedListener)
    {
        if(connected)
        {
            ArrayList<CalorieDate> caloriesBurnedThisWeek = new ArrayList<>();

            for(int i = 0; i < 7; i++)
            {
                caloriesBurnedThisWeek.add(new CalorieDate(i * 100, "Monday"));
            }

            onFinishedListener.onFinished(caloriesBurnedThisWeek);
        }
        else
        {
            caloriesBurnedThisWeekListener = onFinishedListener;
        }
    }

    @Override
    public void getCalorieActivitiesToday(Task<List<CalorieActivity>> onFinishedListener)
    {
        if(connected)
        {
            ArrayList<CalorieActivity> list = new ArrayList<>();
            list.add(new CalorieActivity(350, "Running"));
            list.add(new CalorieActivity(200, "Walking"));
            list.add(new CalorieActivity(400, "Lifting"));
            onFinishedListener.onFinished(list);
        }
        else
        {
            todayActivitiesListener = onFinishedListener;
        }
    }

    @Override
    public void getMonthDayActivities(Task<List<DayActivities>> onFinishedListener)
    {
        if(connected)
        {
            //creates a list where we're putting DayActivities objects
            ArrayList<DayActivities> monthList = new ArrayList<>();
            if(isThereData)
            {
                Log.i("MyApp", "There is data");
                //making a bunch of lists for the week
                ArrayList<CalorieActivity> day1List = new ArrayList<>();
                ArrayList<CalorieActivity> day2List = new ArrayList<>();
                ArrayList<CalorieActivity> day3List = new ArrayList<>();
                ArrayList<CalorieActivity> day4List = new ArrayList<>();
                ArrayList<CalorieActivity> day5List = new ArrayList<>();
                //creating new DayActivities objects with the specific days
                DayActivities day1 = new DayActivities(2016, 5, 5, day1List);
                DayActivities day2 = new DayActivities(2016, 5, 8, day2List);
                DayActivities day3 = new DayActivities(2016, 5, 12, day3List);
                DayActivities day4 = new DayActivities(2016, 5, 20, day4List);
                DayActivities day5 = new DayActivities(2016, 5, 28, day5List);
                //adding each day to the list
                monthList.add(day1);
                monthList.add(day2);
                monthList.add(day3);
                monthList.add(day4);
                monthList.add(day5);
            }

            onFinishedListener.onFinished(monthList);
        }
        else
        {
            monthDayActivitiesListener = onFinishedListener;
        }
    }

    @Override
    public void loadActivityDataPointsAsync(Task<List<ActivityDataPoint>> onFinishedListener, Calendar startDate, Calendar endDate)
    {
        if(connected)
        {
            ArrayList<ActivityDataPoint> list = new ArrayList<>();

            ActivityDataPoint one = new ActivityDataPoint("Swimming", 350, "I swam", 2016, 5, 24, 6, 30, 2016, 5, 24, 7, 30);
            ActivityDataPoint two = new ActivityDataPoint("Lifting", 200, "I picked stuff up", 2016, 5, 25, 6, 30, 2016, 5, 25, 7, 30);
            ActivityDataPoint three = new ActivityDataPoint("Running", 500, "I ran", 2016, 5, 26, 6, 45, 2016, 5, 26, 8, 15);

            list.add(one);
            list.add(two);
            list.add(three);

            onFinishedListener.onFinished(list);
        }
        else
        {
            loadActivityDataPointsListener = onFinishedListener;
        }
    }

    public static void setIsThereData(boolean data)
    {
        isThereData = data;
    }

    public static void reset()
    {
        setIsThereData(true);
    }


    @Override
    public List<ActivityDataPoint> loadActivityDataPoints(Calendar startDate, Calendar endDate)
    {
        return null;
    }

    @Override
    public void subscribe(IRepositoryChangeListener listener)
    {
        stateListeners.add(listener);
    }

    @Override
    public void onSubscribe(IRepositoryChangeListener listener)
    {
        stateListeners.remove(listener);
    }



    public void stateChange(final RepositoryState newState, Activity activity)
    {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(newState == RepositoryState.CONNECTION_SUCCESS)
                {
                    onConnected();
                }
                for(IRepositoryChangeListener listener : stateListeners)
                {
                    listener.onRepositoryStateChanged(currentState, newState);
                }
                currentState = newState;

            }
        });
    }

    public void onConnected()
    {
        connected = true;
        if(caloriesBurnedTodayListener != null)
        {
            this.getCaloriesBurnedToday(caloriesBurnedTodayListener);
            caloriesBurnedTodayListener = null;
        }

        if(caloriesBurnedThisWeekListener != null)
        {
            this.getCaloriesBurnedThisWeek(caloriesBurnedThisWeekListener);
            caloriesBurnedThisWeekListener = null;
        }

        if(todayActivitiesListener != null)
        {
            this.getCalorieActivitiesToday(todayActivitiesListener);
            todayActivitiesListener = null;
        }

        if(monthDayActivitiesListener != null)
        {
            this.getMonthDayActivities(monthDayActivitiesListener);
            monthDayActivitiesListener = null;
        }

        if(loadActivityDataPointsListener != null)
        {
            this.loadActivityDataPointsAsync(loadActivityDataPointsListener, startDate, endDate);
            loadActivityDataPointsListener = null;
        }
    }
}

