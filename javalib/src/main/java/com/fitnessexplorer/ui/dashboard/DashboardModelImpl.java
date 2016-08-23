package com.fitnessexplorer.ui.dashboard;


import com.fitnessexplorer.entities.Calorie;
import com.fitnessexplorer.entities.CalorieActivity;
import com.fitnessexplorer.entities.CalorieDate;
import com.fitnessexplorer.entities.DayActivities;
import com.fitnessexplorer.services.repo.RepositoryState;
import com.fitnessexplorer.services.repo.Task;
import com.fitnessexplorer.services.repo.preferences.IPreferencesRepository;
import com.fitnessexplorer.ui.base.BaseModel;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import java.io.Serializable;
import java.util.List;

/**
 * Created by David.Bickford on 5/24/2016.
 */
public class DashboardModelImpl extends BaseModel<IDashboardView, IDashboardController> implements IDashboardModel, Serializable
{
    private boolean isThereData= false;
    private boolean dialogShown;

    private final Task<Calorie> onCaloriesBurnedTodayLoaded = new Task<Calorie>()
    {
        @Override
        public void onFinished(Calorie data)
        {
            view.caloriesBurnedTodayLoaded(data);
        }
    };

    private final Task<List<CalorieDate>> onCaloriesBurnedThisWeekLoaded = new Task<List<CalorieDate>>()
    {
        @Override
        public void onFinished(List<CalorieDate> data)
        {
            view.caloriesBurnedThisWeekLoaded(data);
        }
    };

    private final Task<List<CalorieActivity>> onActivitiesDoneTodayLoaded = new Task<List<CalorieActivity>>()
    {
        @Override
        public void onFinished(List<CalorieActivity> data)
        {
            view.activitiesForTodayLoaded(data);
        }
    };

    private final Task<List<DayActivities>> onMonthDayActivitiesLoaded = new Task<List<DayActivities>>()
    {
        @Override
        public void onFinished(List<DayActivities> data)
        {
            if(data.size() > 0)
            {
                isThereData = true;
            }
            else
            {
                isThereData = false;
            }
            view.activitiesThisMonthLoaded(data);
        }
    };

    public DashboardModelImpl(IFitnessRepository repo, IPreferencesRepository preferencesRepo, IDashboardController controller)
    {
        super(repo, preferencesRepo, controller);
    }

    @Override
    public void viewReady(IDashboardView view)
    {
        super.viewReady(view);
        this.fitnessRepository.getCaloriesBurnedToday(onCaloriesBurnedTodayLoaded);
        this.fitnessRepository.getCaloriesBurnedThisWeek(onCaloriesBurnedThisWeekLoaded);
        this.fitnessRepository.getCalorieActivitiesToday(onActivitiesDoneTodayLoaded);
        this.fitnessRepository.getMonthDayActivities(onMonthDayActivitiesLoaded);
    }

    @Override
    public void browseFitnessDataClicked()
    {
        this.controller.navigateToRawDataView();
    }

    @Override
    public void onRepositoryStateChanged(RepositoryState oldState, RepositoryState newState)
    {
        if(newState == RepositoryState.CONNECTING)
        {
            view.showConnecting();
        }
        else if(newState == RepositoryState.CONNECTION_SUCCESS && !isThereData)
        {
            view.showNoData();
        }
        else if(newState == RepositoryState.CONNECTION_SUCCESS && isThereData)
        {
            view.showData();
        }
        else if(newState == RepositoryState.CONNECTION_FAILED)
        {
            if(!dialogShown)
            {
                view.showFitNotConnectedDialog();
                dialogShown = true;
            }
        }
        else if(newState == RepositoryState.CONNECTION_CANCEL)
        {
            if(!dialogShown)
            {
                view.showFitNotConnectedDialog();
                dialogShown = true;
            }
        }
    }
}
