package com.fitnessexplorer.services.repo.simulated;

import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.IRepositoryChangeListener;
import com.fitnessexplorer.services.repo.RepositoryState;
import com.fitnessexplorer.services.repo.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class SimulatedFitnessRepository implements IFitnessRepository
{
    private static final SimpleDateFormat DAY_OF_WEEK_FORMATTER = new SimpleDateFormat("EE");

    private static List<String> RANDOM_ACTIVITIES = new ArrayList<String>()
    {{
        add("Walking");
        add("Swimming");
        add("Biking");
        add("Running");
        add("Boxing");
    }};

    private List<IRepositoryChangeListener> stateListeners = new ArrayList<>();

    @Override
    public void getCaloriesBurnedToday(Task<Calorie> onFinishedListener)
    {
        onFinishedListener.onFinished(new Calorie(546));

        connected();
    }

    @Override
    public void getCalorieActivitiesToday(Task<List<CalorieActivity>> onFinishedListener)
    {
        onFinishedListener.onFinished(generateRandom(4));

        connected();
    }

    @Override
    public void getCaloriesBurnedThisWeek(Task<List<CalorieDate>> onFinishedListener)
    {
        Random random = new Random();

        List<CalorieDate> list = new ArrayList<>();

        Calendar calendar = GregorianCalendar.getInstance();

        for (int i = 0; i < 7; i++)
        {
            list.add(new CalorieDate(random.nextInt(1000), DAY_OF_WEEK_FORMATTER.format(calendar.getTime())));

            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }

        Collections.reverse(list);

        onFinishedListener.onFinished(list);

        connected();
    }

    @Override
    public void getMonthDayActivities(Task<List<DayActivities>> onFinishedListener)
    {
        Random random = new Random();

        List<DayActivities> list = new ArrayList<>();

        Calendar calendar = GregorianCalendar.getInstance();

        for (int i = 0; i < 25; i++)
        {
            list.add(new DayActivities(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), generateRandom(random.nextInt(3) + 1)));

            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }

        onFinishedListener.onFinished(list);

        connected();
    }

    @Override
    public void loadActivityDataPointsAsync(Task<List<ActivityDataPoint>> onFinishedListener, Calendar startDate, Calendar endDate)
    {
        onFinishedListener.onFinished(loadActivityDataPoints(startDate, endDate));
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
    public void unsubscribe(IRepositoryChangeListener listener)
    {
        stateListeners.remove(listener);
    }

    private void connected()
    {
        for (IRepositoryChangeListener listener : stateListeners)
        {
            listener.onRepositoryStateChanged(RepositoryState.CONNECTING, RepositoryState.CONNECTION_SUCCESS);
        }
    }

    private List<CalorieActivity> generateRandom(int size)
    {
        Collections.shuffle(RANDOM_ACTIVITIES);

        Random random = new Random();

        List<CalorieActivity> list = new ArrayList<>();

        for (int i = 0; i < size; i++)
        {
            list.add(new CalorieActivity(random.nextInt(1000), RANDOM_ACTIVITIES.get(i)));
        }

        return list;
    }
}
