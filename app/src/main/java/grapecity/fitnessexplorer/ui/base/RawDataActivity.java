package grapecity.fitnessexplorer.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.fitnessexplorer.ui.rawdata.IRawDataController;
import grapecity.fitnessexplorer.R;

/**
 * Created by David.Bickford on 6/6/2016.
 */
public class RawDataActivity extends BaseActivity implements IRawDataController
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        RawDataFragment articleFragment = new RawDataFragment();
        transaction.replace(R.id.fragment_container, articleFragment);
        transaction.commit();
    }
}
