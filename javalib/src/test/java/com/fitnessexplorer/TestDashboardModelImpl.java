package com.fitnessexplorer;

/**
 * Created by David.Bickford on 5/24/2016.
 */

import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.ui.dashboard.DashboardModelImpl;
import com.fitnessexplorer.ui.dashboard.IDashboardController;
import com.fitnessexplorer.ui.dashboard.IDashboardView;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDashboardModelImpl
{
    DashboardModelImpl model;
    MockDashboardController controller;
    MockDashboardView view;
    IFitnessRepository repo;

    @Before
    public void init()
    {
        view = new MockDashboardView();
        controller = new MockDashboardController();
        repo = new MockFitnessRepository();
        model = new DashboardModelImpl(repo, controller);
    }

    @Test
    public void testBrowseFitnessDataClicked()
    {
        model.browseFitnessDataClicked();
        assertEquals(true, controller.navigateToRawDataViewClicked);
    }

    @Test
    public void testViewReady()
    {
        model.viewReady(view);
        assertEquals(true, view.caloriesBurnedTodayLoadedCalled);
        assertEquals(true, view.activitiesTodayCalled);
        assertEquals(true, view.caloriesBurnedThisWeekCalled);
        assertEquals(true, view.activitiesThisMonthCalled);
    }

    private class MockDashboardView implements IDashboardView
    {

        boolean caloriesBurnedTodayLoadedCalled = false;
        boolean activitiesTodayCalled = false;
        boolean caloriesBurnedThisWeekCalled = false;
        boolean activitiesThisMonthCalled = false;
        boolean fitNotConnectedCalled = false;
        boolean showDataCalled = false;
        boolean showNoDataCalled = false;
        boolean showConnectingCalled = false;

        @Override
        public void caloriesBurnedTodayLoaded(Calorie cal)
        {
            caloriesBurnedTodayLoadedCalled = true;
        }

        @Override
        public void activitiesForTodayLoaded(List<CalorieActivity> activities)
        {
            activitiesTodayCalled = true;
        }

        @Override
        public void caloriesBurnedThisWeekLoaded(List<CalorieDate> activitiesThisWeek)
        {
            caloriesBurnedThisWeekCalled = true;
        }

        @Override
        public void activitiesThisMonthLoaded(List<DayActivities> activitiesThisMonth)
        {
            activitiesThisMonthCalled = true;
        }

        @Override
        public void showFitNotConnectedDialog()
        {
            fitNotConnectedCalled = true;
        }

        @Override
        public void showData()
        {
            showDataCalled = true;
        }

        @Override
        public void showNoData()
        {
            showNoDataCalled = true;
        }

        @Override
        public void showConnecting()
        {
            showConnectingCalled = true;
        }
    }

    private class MockDashboardController implements IDashboardController
    {
        boolean navigateToRawDataViewClicked = false;

        @Override
        public void navigateToRawDataView()
        {
            navigateToRawDataViewClicked = true;
        }
    }


}
