package com.fitnessexplorer;

/**
 * Created by David.Bickford on 5/25/2016.
 */
import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.IRepositoryChangeListener;
import com.fitnessexplorer.services.repo.Task;
import com.fitnessexplorer.ui.dashboard.DashboardModelImpl;
import com.fitnessexplorer.ui.dashboard.IDashboardController;
import com.fitnessexplorer.ui.factories.ModelFactory;
import com.fitnessexplorer.ui.rawdata.IRawDataController;
import com.fitnessexplorer.ui.rawdata.RawDataModelImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestModelFactory
{
    @Test
    public void newModel()
    {
        boolean inCorrectOne = false;
        IFitnessRepository repo = new IFitnessRepository()
        {
            @Override
            public void getCalorieActivitiesToday(Task<List<CalorieActivity>> onFinishedListener)
            {

            }

            @Override
            public void getCaloriesBurnedThisWeek(Task<List<CalorieDate>> onFinishedListener)
            {

            }

            @Override
            public void unsubscribe(IRepositoryChangeListener listener)
            {

            }

            @Override
            public void loadActivityDataPointsAsync(Task<List<ActivityDataPoint>> onFinishedListener, Calendar startDate, Calendar endDate)
            {

            }

            @Override
            public void subscribe(IRepositoryChangeListener listener)
            {

            }

            @Override
            public void getMonthDayActivities(Task<List<DayActivities>> onFinishedListener)
            {

            }

            @Override
            public void getCaloriesBurnedToday(Task<Calorie> onFinishedListener)
            {

            }

            @Override
            public ArrayList<ActivityDataPoint> loadActivityDataPoints(Calendar startDate, Calendar endDate)
            {
                return null;
            }
        };
        IDashboardController dashboardController = new IDashboardController()
        {
            @Override
            public void navigateToRawDataView()
            {

            }
        };
        IRawDataController rawDataController = new IRawDataController()
        {
            @Override
            public int hashCode()
            {
                return super.hashCode();
            }
        };
//        ModelFactory model = new ModelFactory();
        DashboardModelImpl newModelImpl = new DashboardModelImpl(repo, dashboardController);
        if(ModelFactory.getNewControllerModel(repo, dashboardController) instanceof DashboardModelImpl)
        {
            inCorrectOne = true;
        }
        assertEquals(true, inCorrectOne);
        inCorrectOne = false;
        if(ModelFactory.getNewControllerModel(repo, rawDataController) instanceof RawDataModelImpl)
        {
            inCorrectOne = true;
        }
        assertEquals(true, inCorrectOne);
    }
}
