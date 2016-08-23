package com.fitnessexplorer;

import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.IRepositoryChangeListener;
import com.fitnessexplorer.services.repo.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MockFitnessRepository implements IFitnessRepository
{
    @Override
    public void getCaloriesBurnedToday(Task<Calorie> onFinishedListener)
    {
        Calorie calorie = new Calorie(5);

        onFinishedListener.onFinished(calorie);
    }

    @Override
    public void getCaloriesBurnedThisWeek(Task<List<CalorieDate>> onFinishedListener)
    {
        ArrayList<DayActivities> weekList = new ArrayList<>();

        ArrayList<CalorieActivity> day1List = new ArrayList<>();
        ArrayList<CalorieActivity> day2List = new ArrayList<>();
        ArrayList<CalorieActivity> day3List = new ArrayList<>();
        ArrayList<CalorieActivity> day4List = new ArrayList<>();
        ArrayList<CalorieActivity> day5List = new ArrayList<>();

        DayActivities day1 = new DayActivities(2016, 5, 23, day1List);
        DayActivities day2 = new DayActivities(2016, 5, 24, day2List);
        DayActivities day3 = new DayActivities(2016, 5, 25, day3List);
        DayActivities day4 = new DayActivities(2016, 5, 26, day4List);
        DayActivities day5 = new DayActivities(2016, 5, 27, day5List);

        weekList.add(day1);
        weekList.add(day2);
        weekList.add(day3);
        weekList.add(day4);
        weekList.add(day5);

        ArrayList<CalorieDate> caloriesBurnedThisWeek = new ArrayList<>();

        for(int i=0; i<weekList.size(); i++)
        {
            DayActivities curr = weekList.get(i);
            List<CalorieActivity> activities = curr.getActivities();
            CalorieDate newDate = new CalorieDate(0, "Mon");
            for(int j=0; j<activities.size(); j++)
            {
                newDate.setCalorie(newDate.getCalorie() + activities.get(j).getCalorie());
            }
            caloriesBurnedThisWeek.add(newDate);
        }
        onFinishedListener.onFinished(caloriesBurnedThisWeek);
    }

    @Override
    public void getCalorieActivitiesToday(Task<List<CalorieActivity>> onFinishedListener)
    {
        ArrayList<CalorieActivity> list = new ArrayList<>();
        list.add(new CalorieActivity(350, "Running"));
        list.add(new CalorieActivity(200, "Walking"));
        list.add(new CalorieActivity(400, "Lifting"));
        onFinishedListener.onFinished(list);
    }

    @Override
    public void getMonthDayActivities(Task<List<DayActivities>> onFinishedListener)
    {
//creates a list where we're putting DayActivities objects
        ArrayList<DayActivities> monthList = new ArrayList<>();

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


        onFinishedListener.onFinished(monthList);
    }

    @Override
    public void loadActivityDataPointsAsync(Task<List<ActivityDataPoint>> onFinishedListener, Calendar startDate, Calendar endDate)
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

    @Override
    public List<ActivityDataPoint> loadActivityDataPoints(Calendar startDate, Calendar endDate)
    {
        return null;
    }

    @Override
    public void subscribe(IRepositoryChangeListener listener)
    {

    }

    @Override
    public void onSubscribe(IRepositoryChangeListener listener)
    {

    }
}
