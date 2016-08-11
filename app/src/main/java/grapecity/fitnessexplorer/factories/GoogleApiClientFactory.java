package grapecity.fitnessexplorer.factories;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;

import grapecity.fitnessexplorer.services.GoogleFitRepository;

/**
 * Created by David.Bickford on 7/5/2016.
 */
public class GoogleApiClientFactory
{
    private static int currID = 0;

    public static GoogleApiClient getNewClient(Activity activity, GoogleFitRepository repo)
    {
        GoogleApiClient mClient = new GoogleApiClient.Builder(activity.getApplicationContext())
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .addConnectionCallbacks(repo)
                .enableAutoManage((FragmentActivity)activity, currID, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.i("MyApp", "Google Play services connection failed. Cause: " +
                                result.toString());

                    }})
                .build();

        currID++;
        return mClient;
    }
}
