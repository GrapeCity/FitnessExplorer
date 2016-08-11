package grapecity.fitnessexplorer.ui.base;

import android.support.test.espresso.ViewInteraction;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RawDataViewTests
{

    @Rule
    public ActivityTestRule<RawDataActivity> mActivityTestRule = new ActivityTestRule<>(RawDataActivity.class);

    @Test
    public void rawDataLoadingTest()
    {
        MemoryFitnessRepository fitnessRepository = (MemoryFitnessRepository)((TestMyApp)mActivityTestRule.getActivity().getApplication()).getRepository(null);
        fitnessRepository.setIsThereData(false);
        fitnessRepository.stateChange(RepositoryState.CONNECTING, mActivityTestRule.getActivity());
        onView(withId(R.id.loadingProgressBarTest)).check(matches(isDisplayed()));
    }

    @Test
    public void rawDataViewTest()
    {
        MemoryFitnessRepository fitnessRepository = (MemoryFitnessRepository)((TestMyApp)mActivityTestRule.getActivity().getApplication()).getRepository(null);
        fitnessRepository.setIsThereData(true);
        fitnessRepository.stateChange(RepositoryState.CONNECTION_SUCCESS, mActivityTestRule.getActivity());
        onView(withId(R.id.FrameLayout_RawData)).check(matches(isDisplayed()));
    }

}
