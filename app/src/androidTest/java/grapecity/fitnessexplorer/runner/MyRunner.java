package grapecity.fitnessexplorer.runner;

/**
 * Created by David.Bickford on 8/8/2016.
 */


import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import grapecity.fitnessexplorer.TestMyApp;


/**
 * Created by chrisripple on 4/26/16.
 */
public class MyRunner extends AndroidJUnitRunner
{
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        return super.newApplication(cl, TestMyApp.class.getName(), context);
    }
}
