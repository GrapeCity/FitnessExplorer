package com.fitnessexplorer;

import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.ui.rawdata.IRawDataController;
import com.fitnessexplorer.ui.rawdata.IRawDataView;
import com.fitnessexplorer.ui.rawdata.RawDataModelImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by David.Bickford on 5/24/2016.
 */
public class TestRawDataModelImpl
{
    RawDataModelImpl model;
    MockRawDataView view;
    MockRawDataController controller;

    IFitnessRepository repo;

    @Before
    public void init()
    {
        view = new MockRawDataView();
//        repo = new MemoryFitnessRepository();
        controller = new MockRawDataController();
        model = new RawDataModelImpl(repo, controller);
    }

    @Test
    public void testViewReady()
    {
        model.viewReady(view);
        assertEquals(true, view.activityDataPointsForTheWeekLoadedCalled);
    }

    private class MockRawDataController implements IRawDataController
    {

    }

    private class MockRawDataView implements IRawDataView
    {
        boolean activityDataPointsForTheWeekLoadedCalled = false;

        @Override
        public void activityDataPointsForTheWeekLoaded(ArrayList<ActivityDataPoint> loadActivityDataPoints)
        {
            activityDataPointsForTheWeekLoadedCalled = true;
        }

        @Override
        public void showConnecting()
        {

        }

        @Override
        public void showData()
        {

        }
    }
}
