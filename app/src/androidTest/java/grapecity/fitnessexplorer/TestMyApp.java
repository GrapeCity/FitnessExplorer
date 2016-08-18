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
    MemoryFitnessRepository repo;

    @Override
    public IFitnessRepository getRepository(Activity activity)
    {
        if(repo == null)
        {
            repo = new MemoryFitnessRepository();
        }

        return repo;
    }

    public void endTest()
    {
        repo = new MemoryFitnessRepository();
    }


    @Override
    public View addProgressViewToLayout(ViewGroup parent, LayoutInflater inflater)
    {
        return getProgressView(R.layout.view_loading_test, parent, inflater);
    }
}
