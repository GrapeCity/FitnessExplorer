package grapecity.fitnessexplorer;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.grapecity.xuni.core.LicenseManager;
import grapecity.fitnessexplorer.services.GoogleFitRepository;

/**
 * Created by David.Bickford on 5/31/2016.
 */
public class MyApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        LicenseManager.KEY = License.KEY;
    }

    public IFitnessRepository getRepository(Activity activity)
    {
        return new GoogleFitRepository(activity);
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
}
