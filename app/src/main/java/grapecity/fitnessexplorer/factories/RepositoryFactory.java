package grapecity.fitnessexplorer.factories;

import android.app.Activity;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import grapecity.fitnessexplorer.services.GoogleFitRepository;

/**
 * Created by David.Bickford on 5/26/2016.
 */
public class RepositoryFactory
{
    public static IFitnessRepository getNewRepository(Activity activity)
    {
        return new GoogleFitRepository(activity);
    }
}
