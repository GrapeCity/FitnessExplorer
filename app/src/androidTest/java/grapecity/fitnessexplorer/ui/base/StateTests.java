package grapecity.fitnessexplorer.ui.base;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.fitnessexplorer.services.repo.RepositoryState;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import grapecity.fitnessexplorer.DashboardRobot;
import grapecity.fitnessexplorer.MemoryFitnessRepository;
import grapecity.fitnessexplorer.R;
import grapecity.fitnessexplorer.TestMyApp;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class StateTests
{

    @Rule
    public ActivityTestRule<DashboardActivity> mActivityTestRule = new ActivityTestRule<>(DashboardActivity.class, true, false);

    DashboardRobot robot;

    @Before
    public void before()
    {
        robot = new DashboardRobot(mActivityTestRule);
    }

    @After
    public void after()
    {
        robot.endTest();
    }


    @Test
    public void loadingScreenTest()
    {
        robot
                .launchDashboardScreen()
                .stateChange(RepositoryState.CONNECTING)
                .dashboardView(false)
                .emptyDataView(false)
                .loadingView(true)
                .fitConnectedView(false);
    }

    @Test
    public void emptyDataStateTest()
    {
        robot
                .disableData()
                .launchDashboardScreen()
                .stateChange(RepositoryState.CONNECTION_SUCCESS)
                .dashboardView(false)
                .emptyDataView(true)
                .loadingView(false)
                .fitConnectedView(false);
    }

    @Test
    public void dashboardDataStateTest()
    {
        robot
                .launchDashboardScreen()
                .stateChange(RepositoryState.CONNECTION_SUCCESS)
                .dashboardView(true)
                .emptyDataView(false)
                .loadingView(false)
                .fitConnectedView(false);
    }

    @Test
    public void fitNotConnectedStateTest()
    {
        robot
                .launchDashboardScreen()
                .stateChange(RepositoryState.CONNECTION_FAILED)
                .fitConnectedView(true)
                .stateChange(RepositoryState.CONNECTION_CANCEL)
                .fitConnectedView(true);
    }

    @Test
    public void rawDataViewTest()
    {
        robot
                .launchDashboardScreen()
                .stateChange(RepositoryState.CONNECTION_SUCCESS)
                .dashboardView(true)
                .emptyDataView(false)
                .fitConnectedView(false)
                .scrollToView(R.id.rawdatabutton)
                .clickOnView(R.id.rawdatabutton)
                .loadingView(true);
    }
}

