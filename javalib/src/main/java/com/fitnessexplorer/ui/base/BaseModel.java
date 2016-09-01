package com.fitnessexplorer.ui.base;

import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.IRepositoryChangeListener;
import com.fitnessexplorer.services.repo.Task;
import com.fitnessexplorer.services.repo.preferences.IPreferencesRepository;
import com.fitnessexplorer.services.task.ITaskScheduler;

/**
 * Created by David.Bickford on 5/25/2016.
 */

public abstract class BaseModel<V extends IView, C extends IController> implements IModel<V>, IRepositoryChangeListener
{
    protected V view;
    protected C controller;
    protected IFitnessRepository fitnessRepository;
    protected IPreferencesRepository preferencesRepo;
    private final ITaskScheduler taskScheduler;

    private boolean isGoogleFitEnabled = false;

    public BaseModel(IFitnessRepository fitnessRepository, IPreferencesRepository preferencesRepo, ITaskScheduler taskScheduler, C controller)
    {
        this.fitnessRepository = fitnessRepository;
        this.preferencesRepo = preferencesRepo;
        this.taskScheduler = taskScheduler;
        this.controller = controller;

        this.fitnessRepository.subscribe(this);

        this.preferencesRepo.isGoogleFitEnabled(new Task<Boolean>()
        {
            @Override
            public void onFinished(Boolean data)
            {
                isGoogleFitEnabled = data;
            }
        });
    }

    @Override
    public void viewReady(V view)
    {
        this.view = view;
        this.view.loadToolbar();

        final V tempView = this.view;

        this.preferencesRepo.hasSeenTutorial(new Task<Boolean>()
        {
            @Override
            public void onFinished(Boolean data)
            {
                if(!data)
                {
                    taskScheduler.delayTask(5000, new Task()
                    {
                        @Override
                        public void onFinished(Object data)
                        {
                            tempView.showTutorial();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void toggleDataSourceClicked()
    {
        this.preferencesRepo.setGoogleFitEnabled(!isGoogleFitEnabled, new Task<Boolean>()
        {
            @Override
            public void onFinished(Boolean data)
            {
                view.reload();
            }
        });
    }

    @Override
    public boolean isGoogleFitEnabled()
    {
        return isGoogleFitEnabled;
    }

    @Override
    public void tutorialOkClicked()
    {
        this.preferencesRepo.setHasSeenTutorial();
    }
}
