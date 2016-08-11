package grapecity.fitnessexplorer;

import android.support.test.rule.ActivityTestRule;
import com.fitnessexplorer.services.repo.RepositoryState;
import grapecity.fitnessexplorer.ui.base.DashboardActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by David.Bickford on 8/10/2016.
 */

public class DashboardRobot
{
    public ActivityTestRule<DashboardActivity> mActivityTestRule;
    public MemoryFitnessRepository memoryFitnessRepository;

    public DashboardRobot(ActivityTestRule<DashboardActivity> mActivityTestRule)
    {
        this.mActivityTestRule = mActivityTestRule;
        memoryFitnessRepository = (MemoryFitnessRepository)((TestMyApp)mActivityTestRule.getActivity().getApplication()).getRepository(null);
    }

    public DashboardRobot loadingView(boolean activated)
    {
        if(activated)
        {
            onView(withId(R.id.loadingProgressBarTest)).check(matches(isDisplayed()));
        }
        else
        {
            onView(withId(R.id.loadingProgressBarTest)).check(matches(not((isDisplayed()))));
        }
        return this;
    }

    public DashboardRobot emptyDataView(boolean activated)
    {
        memoryFitnessRepository.setIsThereData(!activated);
        if(activated)
        {
            onView(withId(R.id.EmptyDataView)).check(matches(isDisplayed()));
        }
        else
        {
            onView(withId(R.id.EmptyDataView)).check(matches(not((isDisplayed()))));
        }
        return this;
    }

    public DashboardRobot dashboardView(boolean activated)
    {
        memoryFitnessRepository.setIsThereData(activated);
        if(activated)
        {
            onView(withId(R.id.DataView)).check(matches(isDisplayed()));
        }
        else
        {
            onView(withId(R.id.DataView)).check(matches(not((isDisplayed()))));
        }
        return this;
    }

    public DashboardRobot fitConnectedView(boolean exists)
    {
        if(exists)
        {
            onView(withId(R.id.fitDialog)).check(matches(isDisplayed()));
            onView(withId(R.id.DataView)).check(doesNotExist());
            onView(withId(R.id.loadingProgressBarTest)).check(doesNotExist());
            onView(withId(R.id.EmptyDataView)).check(doesNotExist());
        }
        else
        {
            onView(withId(R.id.fitDialog)).check(doesNotExist());
        }
        return this;
    }

    public DashboardRobot rawDataView(boolean exists)
    {
        if(exists)
        {
            onView(withId(R.id.FrameLayout_RawData)).check(matches(isDisplayed()));
            onView(withId(R.id.fitDialog)).check(doesNotExist());
            onView(withId(R.id.DataView)).check(doesNotExist());
            onView(withId(R.id.loadingProgressBarTest)).check(doesNotExist());
            onView(withId(R.id.EmptyDataView)).check(doesNotExist());
        }
        else
        {
            onView(withId(R.id.FrameLayout_RawData)).check(doesNotExist());
        }
        return this;
    }

    public DashboardRobot stateChange(RepositoryState state)
    {
        memoryFitnessRepository.stateChange(state, mActivityTestRule.getActivity());
        return this;
    }

    public DashboardRobot scrollToView(int id)
    {
        onView(withId(id)).perform(scrollTo());
        return this;
    }

    public DashboardRobot clickOnView(int id)
    {
        onView(withId(id)).perform(click());
        return this;
    }
}
