package com.fitnessexplorer.services.task;

import com.fitnessexplorer.services.repo.Task;

public interface ITaskScheduler
{
    void delayTask(long milliseconds, Task task);
}
