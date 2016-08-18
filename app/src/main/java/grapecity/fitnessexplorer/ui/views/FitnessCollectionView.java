package grapecity.fitnessexplorer.ui.views;

import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.Task;
import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.flexgrid.FlexGrid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by David.Bickford on 6/8/2016.
 */
public class FitnessCollectionView extends com.grapecity.xuni.core.CursorCollectionView<ActivityDataPoint>
{
    private final IFitnessRepository fitnessRepository;
    private Calendar startDate;
    private Calendar endDate;

    public FitnessCollectionView(IFitnessRepository fitnessRepository)
    {
        super(new ObservableList<ActivityDataPoint>());
        this.fitnessRepository = fitnessRepository;
        this.startDate = Calendar.getInstance();
        this.endDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_YEAR, -7);

        fitnessRepository.loadActivityDataPointsAsync(new Task<List<ActivityDataPoint>>()
        {
            @Override
            public void onFinished(List<ActivityDataPoint> data)
            {
                setSourceCollection(data);
            }
        }, startDate, endDate);
    }

    @Override
    public List<ActivityDataPoint> getPageAsync(Integer integer)
    {
        startDate.add(Calendar.DAY_OF_YEAR, -7);
        endDate.add(Calendar.DAY_OF_YEAR, -7);
        return fitnessRepository.loadActivityDataPoints(startDate, endDate);
    }
}
