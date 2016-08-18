package grapecity.fitnessexplorer.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.fitnessexplorer.entities.ActivityDataPoint;
import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.ui.rawdata.IRawDataController;
import com.fitnessexplorer.ui.rawdata.IRawDataView;
import com.fitnessexplorer.ui.rawdata.RawDataModelImpl;
import com.grapecity.xuni.core.Aggregate;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridAllowResizing;
import com.grapecity.xuni.flexgrid.GridColumn;
import java.util.ArrayList;
import grapecity.fitnessexplorer.MyApp;
import grapecity.fitnessexplorer.R;
import grapecity.fitnessexplorer.ui.views.FitnessCollectionView;

/**
 * Created by David.Bickford on 6/6/2016.
 */
public class RawDataFragment extends Fragment implements IRawDataView
{
    private FitnessCollectionView collectionView;
    private IFitnessRepository fitnessRepository;
    private FlexGrid mGrid;
    private RawDataModelImpl model;
    private View progressBar;
    private LinearLayout rawDataLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_rawdata, container, false);
        mGrid = (FlexGrid)view.findViewById(R.id.flexgrid);

        fitnessRepository = ((MyApp)getActivity().getApplication()).getRepository(getActivity());
        collectionView = new FitnessCollectionView(fitnessRepository);
        mGrid.setAutoGenerateColumns(false);
        mGrid.setCollectionView(collectionView);
        model = new RawDataModelImpl(fitnessRepository, (IRawDataController)getActivity());
        rawDataLayout = (LinearLayout)view.findViewById(R.id.LinearLayout_RawData);
        progressBar = ((MyApp)getActivity().getApplication()).addProgressViewToLayout(view, inflater);
        showConnecting();

        GridColumn columnDate = new GridColumn(mGrid, "Date", "startDate");
        GridColumn columnActivity = new GridColumn(mGrid, "Activity", "activity");
        GridColumn columnDescription = new GridColumn(mGrid, "Description", "activityDescription");
        GridColumn columnCalories = new GridColumn(mGrid, "Calories", "calories");
        GridColumn columnStart = new GridColumn(mGrid, "Start Time", "startDate");
        GridColumn columnEnd = new GridColumn(mGrid, "End Time", "endDate");

        columnDate.setFormat("M/d");
        columnStart.setFormat("hh:mm a");
        columnEnd.setFormat("hh:mm a");
        columnCalories.setAggregate(Aggregate.SUM);

        mGrid.getColumns().add(columnDate);
        mGrid.getColumns().add(columnActivity);
        mGrid.getColumns().add(columnDescription);
        mGrid.getColumns().add(columnCalories);
        mGrid.getColumns().add(columnStart);
        mGrid.getColumns().add(columnEnd);

        mGrid.setAllowResizing(GridAllowResizing.COLUMNS);
        mGrid.setSelectionBackgroundColor(Color.parseColor("#3F51B5"));
        mGrid.setReadOnly(true);

        model.viewReady(this);
        return view;
    }

    @Override
    public void activityDataPointsForTheWeekLoaded(ArrayList<ActivityDataPoint> loadActivityDataPoints)
    {

    }

    @Override
    public void showData()
    {
        progressBar.setVisibility(View.GONE);
        rawDataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showConnecting()
    {
        progressBar.setVisibility(View.VISIBLE);
        rawDataLayout.setVisibility(View.GONE);
    }
}