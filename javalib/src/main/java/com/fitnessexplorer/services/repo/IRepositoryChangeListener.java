package com.fitnessexplorer.services.repo;

/**
 * Created by David.Bickford on 7/13/2016.
 */
public interface IRepositoryChangeListener
{
    void onRepositoryStateChanged(RepositoryState oldState, RepositoryState newState);
}
