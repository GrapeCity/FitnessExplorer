package grapecity.fitnessexplorer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitnessexplorer.services.repo.IFitnessRepository;

/**
 * Created by David.Bickford on 8/8/2016.
 */

public class TestMyApp extends MyApp
{
    MemoryFitnessRepository repo = new MemoryFitnessRepository();

    @Override
    public IFitnessRepository getRepository(Activity activity)
    {
        return repo;
    }

    @Override
    public View addProgressViewToLayout(ViewGroup parent, LayoutInflater inflater)
    {
        return getProgressView(R.layout.view_loading_test, parent, inflater);
    }
}
