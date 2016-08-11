package com.fitnessexplorer.ui.base;

import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.IRepositoryChangeListener;

/**
 * Created by David.Bickford on 5/25/2016.
 */

public abstract class BaseModel<V extends IView, C extends IController> implements IModel<V>, IRepositoryChangeListener
{
    protected V view;
    protected C controller;
    protected IFitnessRepository repo;

    public BaseModel(IFitnessRepository repo, C controller)
    {
        this.repo = repo;
        this.controller = controller;

        this.repo.subscribe(this);
    }

    @Override
    public void viewReady(V view)
    {
        this.view = view;
    }


}
