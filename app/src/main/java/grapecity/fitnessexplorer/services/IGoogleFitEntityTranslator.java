package grapecity.fitnessexplorer.services;

import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.result.DataReadResult;

/**
 * Created by David.Bickford on 7/6/2016.
 */
public interface IGoogleFitEntityTranslator
{
    int translateFieldToCalorie(DataPoint dp);

    int translateFieldToActivity(DataPoint dp);

    int translateBucketToCalorie(DataReadResult dataReadResult);
}
