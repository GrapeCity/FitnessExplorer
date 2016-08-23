package grapecity.fitnessexplorer.services.repo.preferences;

import android.content.SharedPreferences;

import com.fitnessexplorer.services.repo.Task;
import com.fitnessexplorer.services.repo.preferences.IPreferencesRepository;

public class SharedPreferencesRepository implements IPreferencesRepository
{
    private static final String GFIT_ENABLED = "GFIT_ENABLED";

    private SharedPreferences sharedPreferences;

    private final IPreferencesChangedListener changedListener;

    public SharedPreferencesRepository(SharedPreferences sharedPreferences, IPreferencesChangedListener changedListener)
    {
        this.sharedPreferences = sharedPreferences;
        this.changedListener = changedListener;
    }

    @Override
    public void isGoogleFitEnabled(Task<Boolean> onCompleted)
    {
        onCompleted.onFinished(sharedPreferences.getBoolean(GFIT_ENABLED, false));
    }

    @Override
    public void setGoogleFitEnabled(boolean enabled, Task<Boolean> onCompleted)
    {
        try
        {
            sharedPreferences.edit().putBoolean(GFIT_ENABLED, enabled).commit();

            changedListener.onChange(enabled);

            onCompleted.onFinished(true);
        }
        catch (Exception e)
        {

        }

        onCompleted.onFinished(false);
    }
}