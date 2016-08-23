package grapecity.fitnessexplorer;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.Task;
import com.fitnessexplorer.services.repo.preferences.IPreferencesRepository;
import com.fitnessexplorer.services.repo.simulated.SimulatedFitnessRepository;
import com.grapecity.xuni.core.LicenseManager;

import grapecity.fitnessexplorer.services.repo.googlefit.GoogleFitRepository;
import grapecity.fitnessexplorer.services.repo.preferences.IPreferencesChangedListener;
import grapecity.fitnessexplorer.services.repo.preferences.SharedPreferencesRepository;

/**
 * Created by David.Bickford on 5/31/2016.
 */
public class MyApp extends Application implements IPreferencesChangedListener
{
    IPreferencesRepository preferencesRepository;

    IFitnessRepository fitnessRepository;

    private boolean googleFitEnabled = false;

    @Override
    public void onCreate()
    {
        super.onCreate();
        LicenseManager.KEY = License.KEY;

        preferencesRepository = new SharedPreferencesRepository(getSharedPreferences("FITNESS EXPLORER", MODE_PRIVATE), this);

        preferencesRepository.isGoogleFitEnabled(new Task<Boolean>()
        {
            @Override
            public void onFinished(Boolean data)
            {
                googleFitEnabled = data;
            }
        });
    }

    public IFitnessRepository getRepository(Activity activity)
    {
        return googleFitEnabled ? new GoogleFitRepository(activity) : new SimulatedFitnessRepository();
    }

    public IPreferencesRepository getPreferencesRepository()
    {
        return preferencesRepository;
    }

    public View addProgressViewToLayout(ViewGroup parent, LayoutInflater inflater)
    {
        return getProgressView(R.layout.view_loading_indicator, parent, inflater);
    }

    protected View getProgressView(@LayoutRes int resource, ViewGroup parent, LayoutInflater inflater)
    {
        View progressView = inflater.inflate(resource, parent, false);
        parent.addView(progressView);
        return progressView;
    }

    @Override
    public void onChange(boolean isGoogleFitEnabled)
    {
        googleFitEnabled = isGoogleFitEnabled;
    }
}

