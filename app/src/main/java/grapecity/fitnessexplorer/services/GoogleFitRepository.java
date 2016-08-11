package grapecity.fitnessexplorer.services;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.fitnessexplorer.services.repo.IRepositoryChangeListener;
import com.fitnessexplorer.services.repo.RepositoryState;
import com.fitnessexplorer.services.repo.Task;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import grapecity.fitnessexplorer.entities.ActivityTypes;
import grapecity.fitnessexplorer.entities.SetCalendar;
import grapecity.fitnessexplorer.factories.ReadRequestFactory;

/**
 * Created by David.Bickford on 6/20/2016.
 */
public class GoogleFitRepository implements IFitnessRepository, GoogleApiClient.ConnectionCallbacks
{
    private GoogleApiClient mClient = null;
    private ArrayList<CalorieActivity> calorieActivitiesToday;
    private ArrayList<DayActivities> dayActivities;
    private ArrayList<CalorieDate> calorieDates;
    private boolean connected = false;
    private Task<Calorie> caloriesBurnedTodayListener = null;
    private Task<List<CalorieDate>> caloriesBurnedThisWeekListener = null;
    private Task<List<CalorieActivity>> todayActivitiesListener = null;
    private Task<List<DayActivities>> monthDayActivitiesListener = null;
    private Task<List<ActivityDataPoint>> loadActivityDataPointsListener = null;
    private Calendar startDate;
    private Calendar endDate;
    private GoogleFitEntityTranslator entityTranslator;
    private List<IRepositoryChangeListener> stateListeners = new ArrayList<>();
    private RepositoryState currentState = RepositoryState.CONNECTING;

    public GoogleFitRepository(Activity activity)
    {
        mClient = new GoogleApiClient.Builder(activity.getApplicationContext())
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .addConnectionCallbacks(this)
                .enableAutoManage((FragmentActivity)activity, 0, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.i("MyApp", "Google Play services connection failed. Cause: " +
                                result.toString());
                        stateChange(RepositoryState.CONNECTION_FAILED);
                    }})
                .build();
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        calorieActivitiesToday = new ArrayList<>();
        dayActivities = new ArrayList<>();
        entityTranslator = new GoogleFitEntityTranslator();
    }

    @Override
    public void getCaloriesBurnedToday(final Task<Calorie> onFinishedListener)
    {
        if(!connected)
        {
            this.caloriesBurnedTodayListener = onFinishedListener;
            stateChange(mClient.isConnected() ? RepositoryState.CONNECTION_SUCCESS : RepositoryState.CONNECTING);
        }
        else
        {
            new AsyncTask<Void, Void, Calorie>()
            {
                @Override
                protected Calorie doInBackground(Void... params)
                {
                    Calendar cal = Calendar.getInstance();
                    Date now = new Date();
                    cal.setTime(now);
                    Calorie calorie = new Calorie();

                    long endTime = cal.getTimeInMillis();
                    SetCalendar.setStartTime(cal);
                    long startTime = cal.getTimeInMillis();

                    DataReadRequest readRequest = ReadRequestFactory.getCaloriesReadRequest(startTime, endTime);
                    DataReadResult dataReadResult = Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);
                    calorie.setCalorie(entityTranslator.translateBucketToCalorie(dataReadResult));

                    return calorie;
                }

                @Override
                protected void onPostExecute(Calorie calorie)
                {
                    onFinishedListener.onFinished(calorie);
                    caloriesBurnedTodayListener = null;
                    stateChange(RepositoryState.CONNECTING);
                }
            }.execute();
        }
    }

    @Override
    public void getCaloriesBurnedThisWeek(final Task<List<CalorieDate>> onFinishedListener)
    {
        if(!connected)
        {
            this.caloriesBurnedThisWeekListener = onFinishedListener;
            stateChange(mClient.isConnected() ? RepositoryState.CONNECTION_SUCCESS : RepositoryState.CONNECTING);
        }
        else
        {
            new AsyncTask<Void, Void, List<CalorieDate>>()
            {
                @Override
                protected List<CalorieDate> doInBackground(Void... params)
                {
                    Calendar cal = Calendar.getInstance();
                    Date now = new Date();
                    cal.setTime(now);
                    calorieDates = new ArrayList<>();


                    long endTime = cal.getTimeInMillis();
                    SetCalendar.setLessOneWeek(cal);
                    long startTime = cal.getTimeInMillis();

                    DataReadRequest readRequest = ReadRequestFactory.getCaloriesReadRequest(startTime, endTime);
                    DataReadResult dataReadResult = Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);

                    for (Bucket bucket : dataReadResult.getBuckets())
                    {
                        List<DataSet> dataSets = bucket.getDataSets();
                        for (DataSet dataSet : dataSets)
                        {
                            for (DataPoint dp : dataSet.getDataPoints())
                            {
                                CalorieDate currCal = new CalorieDate();
                                Calendar startDate = Calendar.getInstance();

                                startDate.setTime(new Date(dp.getStartTime(TimeUnit.MILLISECONDS)));
                                currCal.setCalendar(startDate);
                                currCal.setCalorie(entityTranslator.translateFieldToCalorie(dp));
                                calorieDates.add(currCal);
                            }
                        }
                    }
                    return calorieDates;
                }

                @Override
                protected void onPostExecute(List<CalorieDate> calorieDates)
                {
                    onFinishedListener.onFinished(calorieDates);
                    caloriesBurnedThisWeekListener = null;
                    stateChange(RepositoryState.CONNECTING);
                }
            }.execute();
        }
    }

    @Override
    public void getCalorieActivitiesToday(final Task<List<CalorieActivity>> onFinishedListener)
    {
        if(!connected)
        {
            this.todayActivitiesListener = onFinishedListener;
            stateChange(mClient.isConnected() ? RepositoryState.CONNECTION_SUCCESS : RepositoryState.CONNECTING);
        }
        else
        {
            new AsyncTask<Void, Void, List<CalorieActivity>>()
            {
                @Override
                protected List<CalorieActivity> doInBackground(Void... params)
                {
                    Calendar cal = Calendar.getInstance();
                    Date now = new Date();
                    cal.setTime(now);

                    long endTime = cal.getTimeInMillis();
                    SetCalendar.setStartTime(cal);
                    long startTime = cal.getTimeInMillis();

                    DataReadRequest readRequest = ReadRequestFactory.getActivityReadRequest(startTime, endTime);
                    DataReadResult dataReadResult = Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);

                    if (dataReadResult.getBuckets().size() > 0)
                    {
                        for (Bucket bucket : dataReadResult.getBuckets())
                        {
                            List<DataSet> dataSets = bucket.getDataSets();
                            for (DataSet dataSet : dataSets)
                            {
                                Calendar startDate;
                                Calendar endDate;
                                CalorieActivity currActivity;

                                for (DataPoint dp : dataSet.getDataPoints())
                                {
                                    startDate = Calendar.getInstance();
                                    endDate = Calendar.getInstance();
                                    currActivity = new CalorieActivity();

                                    startDate.setTime(new Date(dp.getStartTime(TimeUnit.MILLISECONDS)));
                                    currActivity.setStartDate(startDate);
                                    endDate.setTime(new Date(dp.getEndTime(TimeUnit.MILLISECONDS)));
                                    currActivity.setEndDate(endDate);

                                    currActivity.setActivityNumber(entityTranslator.translateFieldToActivity(dp));
                                    currActivity.setActivity(ActivityTypes.findActivity(currActivity.getActivityNumber()));

                                    cal.setTime(currActivity.getStartDate().getTime());
                                    startTime = cal.getTimeInMillis();
                                    cal.setTime(currActivity.getEndDate().getTime());
                                    endTime = cal.getTimeInMillis();

                                    DataReadRequest readRequest2 = ReadRequestFactory.getCaloriesReadRequest(startTime, endTime);
                                    DataReadResult dataReadResult2 = Fitness.HistoryApi.readData(mClient, readRequest2).await(1, TimeUnit.MINUTES);

                                    currActivity.setCalorie(entityTranslator.translateBucketToCalorie(dataReadResult2));

                                    calorieActivitiesToday.add(currActivity);
                                }
                            }
                        }
                    }
                    else
                    {
                        Log.i("MyApp", "No data");
                    }
                    return calorieActivitiesToday;
                }

                @Override
                protected void onPostExecute(List<CalorieActivity> calorieDates)
                {
                    onFinishedListener.onFinished(calorieDates);
                    todayActivitiesListener = null;
                    stateChange(RepositoryState.CONNECTING);
                }
            }.execute();
        }
    }

    @Override
    public void getMonthDayActivities(final Task<List<DayActivities>> onFinishedListener)
    {
        if(!connected)
        {
            this.monthDayActivitiesListener = onFinishedListener;
            stateChange(mClient.isConnected() ? RepositoryState.CONNECTION_SUCCESS : RepositoryState.CONNECTING);
        }
        else
        {
            new AsyncTask<Void, Void, List<DayActivities>>()
            {
                @Override
                protected List<DayActivities> doInBackground(Void... params)
                {
                    Calendar cal = Calendar.getInstance();
                    Date now = new Date();
                    cal.setTime(now);

                    long endTime = cal.getTimeInMillis();
                    SetCalendar.setLessOneMonth(cal);
                    long startTime = cal.getTimeInMillis();

                    DataReadRequest readRequest = ReadRequestFactory.getActivityReadRequest(startTime, endTime);
                    DataReadResult dataReadResult = Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);

                    if (dataReadResult.getBuckets().size() > 0)
                    {
                        for (Bucket bucket : dataReadResult.getBuckets())
                        {
                            List<DataSet> dataSets = bucket.getDataSets();
                            for (DataSet dataSet : dataSets)
                            {
                                Calendar startDate;
                                Calendar endDate;
                                DayActivities currActivity;

                                for (DataPoint dp : dataSet.getDataPoints())
                                {
                                    startDate = Calendar.getInstance();
                                    endDate = Calendar.getInstance();
                                    currActivity = new DayActivities();
                                    CalorieActivity calorieActivity = new CalorieActivity();

                                    startDate.setTime(new Date(dp.getStartTime(TimeUnit.MILLISECONDS)));
                                    currActivity.setStartDate(startDate);
                                    calorieActivity.setStartDate(startDate);
                                    endDate.setTime(new Date(dp.getEndTime(TimeUnit.MILLISECONDS)));
                                    currActivity.setEndDate(endDate);
                                    calorieActivity.setEndDate(endDate);

                                    calorieActivity.setActivityNumber(entityTranslator.translateFieldToActivity(dp));
                                    calorieActivity.setActivity(ActivityTypes.findActivity(calorieActivity.getActivityNumber()));

                                    if(calorieActivity.getActivityNumber() != 0 && calorieActivity.getActivityNumber() != 3 && calorieActivity.getActivityNumber() != 4)
                                    {
                                        cal.setTime(calorieActivity.getStartDate().getTime());
                                        startTime = cal.getTimeInMillis();
                                        cal.setTime(calorieActivity.getEndDate().getTime());
                                        endTime = cal.getTimeInMillis();

                                        DataReadRequest readRequest2 = ReadRequestFactory.getCaloriesReadRequest(startTime, endTime);
                                        DataReadResult dataReadResult2 = Fitness.HistoryApi.readData(mClient, readRequest2).await(1, TimeUnit.MINUTES);
                                        calorieActivity.setCalorie(entityTranslator.translateBucketToCalorie(dataReadResult2));

                                        boolean added = false;
                                        for(int j=0; j<dayActivities.size(); j++)
                                        {
                                            DayActivities currDay = dayActivities.get(j);
                                            if(currActivity.getStartDate().get(Calendar.DAY_OF_MONTH) == currDay.getStartDate().get(Calendar.DAY_OF_MONTH))
                                            {
                                                currDay.addActivity(calorieActivity);
                                                added = true;
                                            }
                                        }
                                        if(!added)
                                        {
                                            DayActivities newDay = new DayActivities();
                                            Calendar start = Calendar.getInstance();
                                            start.setTime(currActivity.getStartDate().getTime());
                                            Calendar end = Calendar.getInstance();
                                            end.setTime(currActivity.getEndDate().getTime());
                                            SetCalendar.setStartTime(start);
                                            SetCalendar.setEndTime(end);
                                            newDay.setStartDate(start);
                                            newDay.setEndDate(end);
                                            newDay.addActivity(calorieActivity);
                                            dayActivities.add(newDay);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        Log.i("MyApp", "No data");
                    }
                    return dayActivities;
                }

                @Override
                protected void onPostExecute(List<DayActivities> dayActivities)
                {
                    onFinishedListener.onFinished(dayActivities);
                    monthDayActivitiesListener = null;
                    stateChange(RepositoryState.CONNECTION_SUCCESS);
                }
            }.execute();
        }
    }

    @Override
    public List<ActivityDataPoint> loadActivityDataPoints(Calendar startDate, Calendar endDate)
    {
        List<ActivityDataPoint> activityDataPointList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        this.startDate = startDate;
        this.endDate = endDate;
        long endTime = endDate.getTimeInMillis();
        long startTime = startDate.getTimeInMillis();
        ActivityDataPoint dataPoint;

        DataReadRequest readRequest = ReadRequestFactory.getActivityReadRequest(startTime, endTime);
        DataReadResult dataReadResult = Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);

        if (dataReadResult.getBuckets().size() > 0)
        {
            for (Bucket bucket : dataReadResult.getBuckets())
            {
                List<DataSet> dataSets = bucket.getDataSets();
                for (DataSet dataSet : dataSets)
                {
                    for (DataPoint dp : dataSet.getDataPoints())
                    {
                        Calendar start = Calendar.getInstance();
                        Calendar end = Calendar.getInstance();
                        dataPoint = new ActivityDataPoint();

                        start.setTime(new Date(dp.getStartTime(TimeUnit.MILLISECONDS)));
                        dataPoint.setStartDate(start);
                        end.setTime(new Date(dp.getEndTime(TimeUnit.MILLISECONDS)));
                        dataPoint.setEndDate(end);

                        dataPoint.setActivityNumber(entityTranslator.translateFieldToActivity(dp));
                        dataPoint.setActivity(ActivityTypes.findActivity(dataPoint.getActivityNumber()));

                        if(dataPoint.getActivityNumber() != 0 && dataPoint.getActivityNumber() != 3 && dataPoint.getActivityNumber() != 4)
                        {
                            cal.setTime(dataPoint.getStartDate().getTime());
                            startTime = cal.getTimeInMillis();
                            cal.setTime(dataPoint.getEndDate().getTime());
                            endTime = cal.getTimeInMillis();

                            DataReadRequest readRequest2 = ReadRequestFactory.getCaloriesReadRequest(startTime, endTime);
                            DataReadResult dataReadResult2 = Fitness.HistoryApi.readData(mClient, readRequest2).await(1, TimeUnit.MINUTES);
                            dataPoint.setCalories(entityTranslator.translateBucketToCalorie(dataReadResult2));
                            dataPoint.setActivityDescription(dataPoint.getCalories() + " calories lost.");
                            activityDataPointList.add(dataPoint);
                        }
                    }
                }
            }
        }
        else
        {
            Log.i("MyApp", "No data");
        }
        return activityDataPointList;
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

    private void stateChange(RepositoryState newState)
    {
        for(IRepositoryChangeListener listener : stateListeners)
        {
            listener.onRepositoryStateChanged(currentState, newState);
        }
        this.currentState = newState;
    }

    @Override
    public void loadActivityDataPointsAsync(final Task<List<ActivityDataPoint>> onFinishedListener, final Calendar startDate, final Calendar endDate)
    {
        if(!connected)
        {
            this.loadActivityDataPointsListener = onFinishedListener;
            this.startDate = startDate;
            this.endDate = endDate;
            stateChange(mClient.isConnected() ? RepositoryState.CONNECTION_SUCCESS : RepositoryState.CONNECTING);
        }
        else
        {
            new AsyncTask<Void, Void, List<ActivityDataPoint>>()
            {
                @Override
                protected List<ActivityDataPoint> doInBackground(Void... params)
                {
                    return loadActivityDataPoints(startDate, endDate);
                }

                @Override
                protected void onPostExecute(List<ActivityDataPoint> dataPointList)
                {
                    onFinishedListener.onFinished(dataPointList);
                    loadActivityDataPointsListener = null;
                    stateChange(RepositoryState.CONNECTION_SUCCESS);
                }
            }.execute();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        connected = true;
        if(caloriesBurnedTodayListener != null)
        {
            this.getCaloriesBurnedToday(caloriesBurnedTodayListener);
        }

        if(caloriesBurnedThisWeekListener != null)
        {
            this.getCaloriesBurnedThisWeek(caloriesBurnedThisWeekListener);
        }

        if(todayActivitiesListener != null)
        {
            this.getCalorieActivitiesToday(todayActivitiesListener);
        }

        if(monthDayActivitiesListener != null)
        {
            this.getMonthDayActivities(monthDayActivitiesListener);
        }

        if(loadActivityDataPointsListener != null)
        {
            this.loadActivityDataPointsAsync(loadActivityDataPointsListener, startDate, endDate);
        }
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        stateChange(RepositoryState.CONNECTION_CANCEL);
    }
}