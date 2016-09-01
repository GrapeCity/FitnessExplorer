package com.fitnessexplorer.services.repo.preferences;


import com.fitnessexplorer.services.repo.Task;

public interface IPreferencesRepository
{
    void isGoogleFitEnabled(Task<Boolean> onCompleted);

    void setGoogleFitEnabled(boolean enabled, Task<Boolean> onCompleted);

    void hasSeenTutorial(Task<Boolean> onCompleted);

    void setHasSeenTutorial();
}