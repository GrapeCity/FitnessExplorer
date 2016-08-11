package com.fitnessexplorer.services.repo;

/**
 * Created by David.Bickford on 6/23/2016.
 */
public interface Task<T>
{
    void onFinished(T data);
}
