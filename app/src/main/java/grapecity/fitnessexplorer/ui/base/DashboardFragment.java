package grapecity.fitnessexplorer.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.ui.base.IController;
import com.fitnessexplorer.ui.dashboard.DashboardModelImpl;
import com.fitnessexplorer.ui.dashboard.IDashboardModel;
import com.fitnessexplorer.ui.dashboard.IDashboardView;
import com.fitnessexplorer.ui.factories.ModelFactory;
import java.util.List;
import grapecity.fitnessexplorer.MyApp;
import grapecity.fitnessexplorer.R;
import grapecity.fitnessexplorer.ui.views.CalendarDashboardView;
import grapecity.fitnessexplorer.ui.views.FlexChartDashboardView;
import grapecity.fitnessexplorer.ui.views.FlexPieDashboardView;
import grapecity.fitnessexplorer.ui.views.GaugeDashboardView;

/**
 * Created by David.Bickford on 5/26/2016.
 */
public class DashboardFragment extends Fragment implements IDashboardView
{
    private ScrollView fitnessExplorerView;
    private IDashboardModel model;
    private GaugeDashboardView gaugeView;
    private FlexPieDashboardView pieView;
    private FlexChartDashboardView chartView;
    private CalendarDashboardView calendarView;
    private IFitnessRepository repo;
    private FitNotConnectedFragment fragment;
    private View progressBar;
    private LinearLayout emptyViewLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.repo = ((MyApp)getActivity().getApplication()).getRepository(getActivity());
        this.model = (DashboardModelImpl) ModelFactory.getNewControllerModel(repo, (IController)getActivity());
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_dashboard, container, false);
        ImageButton rawDataViewButton = (ImageButton)view.findViewById(R.id.rawdatabutton);
        gaugeView = (GaugeDashboardView)view.findViewById(R.id.gauge);
        pieView = (FlexPieDashboardView)view.findViewById(R.id.flexPie);
        chartView = (FlexChartDashboardView)view.findViewById(R.id.flexchart);
        calendarView = (CalendarDashboardView)view.findViewById(R.id.calendar);
        fragment = new FitNotConnectedFragment();

        progressBar = ((MyApp)getActivity().getApplication()).addProgressViewToLayout(view, inflater);
        emptyViewLayout = (LinearLayout)view.findViewById(R.id.EmptyDataView);
        fitnessExplorerView = (ScrollView)view.findViewById(R.id.ScrollView);

        progressBar.setVisibility(View.VISIBLE);
        emptyViewLayout.setVisibility(View.GONE);
        fitnessExplorerView.setVisibility(View.GONE);

        rawDataViewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                model.browseFitnessDataClicked();
            }
        });
        model.viewReady(this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        model.viewReady(this);
    }

    @Override
    public void caloriesBurnedTodayLoaded(Calorie cal)
    {
        gaugeView.setCalories(cal);
    }

    @Override
    public void activitiesForTodayLoaded(List<CalorieActivity> activities)
    {
        pieView.setPie(activities);
    }

    @Override
    public void caloriesBurnedThisWeekLoaded(List<CalorieDate> activitiesThisWeek)
    {
        chartView.setChart(activitiesThisWeek);
    }

    @Override
    public void activitiesThisMonthLoaded(List<DayActivities> activitiesThisMonth)
    {
        calendarView.setCalendar(activitiesThisMonth);
    }

    @Override
    public void showFitNotConnectedDialog()
    {
        if(getFragmentManager() != null)
        {
            progressBar.setVisibility(View.GONE);
            emptyViewLayout.setVisibility(View.VISIBLE);
            fitnessExplorerView.setVisibility(View.GONE);
            fragment.show(getFragmentManager(), "MyApp");
        }
    }

    @Override
    public void showData()
    {
        progressBar.setVisibility(View.GONE);
        emptyViewLayout.setVisibility(View.GONE);
        fitnessExplorerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoData()
    {
        progressBar.setVisibility(View.GONE);
        emptyViewLayout.setVisibility(View.VISIBLE);
        fitnessExplorerView.setVisibility(View.GONE);
    }

    @Override
    public void showConnecting()
    {
        progressBar.setVisibility(View.VISIBLE);
        emptyViewLayout.setVisibility(View.GONE);
        fitnessExplorerView.setVisibility(View.GONE);
    }
}
