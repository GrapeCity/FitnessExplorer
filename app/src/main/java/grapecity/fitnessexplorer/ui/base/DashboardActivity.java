package grapecity.fitnessexplorer.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.fitnessexplorer.ui.dashboard.IDashboardController;
import grapecity.fitnessexplorer.R;

/**
 * Created by David.Bickford on 5/26/2016.
 */
public class DashboardActivity extends BaseActivity implements IDashboardController
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        DashboardFragment articleFragment = new DashboardFragment();

        transaction.replace(R.id.fragment_container, articleFragment);
        transaction.commit();
    }

    @Override
    public void navigateToRawDataView()
    {
        Intent i = new Intent(this, RawDataActivity.class);
        startActivity(i);
    }
}
