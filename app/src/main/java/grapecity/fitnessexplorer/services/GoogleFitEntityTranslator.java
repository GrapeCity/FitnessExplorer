package grapecity.fitnessexplorer.services;

import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.List;

/**
 * Created by David.Bickford on 7/7/2016.
 */
public class GoogleFitEntityTranslator implements IGoogleFitEntityTranslator
{
    @Override
    public int translateFieldToCalorie(DataPoint dp)
    {
        for(Field field : dp.getDataType().getFields())
        {
            if(field.getName().equals("calories"))
            {
                return (int)Double.parseDouble(dp.getValue(field).toString());
            }
        }
        return 0;
    }

    @Override
    public int translateFieldToActivity(DataPoint dp)
    {
        for(Field field : dp.getDataType().getFields())
        {
            if(field.getName().equals("activity"))
            {
                return Integer.parseInt(dp.getValue(field).toString());
            }
        }
        return 4;
    }

    @Override
    public int translateBucketToCalorie(DataReadResult dataReadResult)
    {
        for (Bucket bucket : dataReadResult.getBuckets())
        {
            List<DataSet> dataSets = bucket.getDataSets();
            for (DataSet dataSet : dataSets)
            {
                for (DataPoint dp : dataSet.getDataPoints())
                {
                    return this.translateFieldToCalorie(dp);
                }
            }
        }
        return 0;
    }
}
