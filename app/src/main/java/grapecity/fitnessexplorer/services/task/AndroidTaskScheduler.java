package grapecity.fitnessexplorer.services.task;

import android.os.Handler;

import com.fitnessexplorer.services.repo.Task;
import com.fitnessexplorer.services.task.ITaskScheduler;

public class AndroidTaskScheduler implements ITaskScheduler
{
    @Override
    public void delayTask(long milliseconds, final Task task)
    {
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                task.onFinished(null);
            }
        }, milliseconds);
    }
}
