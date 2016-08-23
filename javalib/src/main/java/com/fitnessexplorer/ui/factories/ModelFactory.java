package com.fitnessexplorer.ui.factories;

import com.fitnessexplorer.services.repo.IFitnessRepository;
import com.fitnessexplorer.services.repo.preferences.IPreferencesRepository;
import com.fitnessexplorer.ui.base.IController;
import com.fitnessexplorer.ui.base.IModel;
import com.fitnessexplorer.ui.dashboard.DashboardModelImpl;
import com.fitnessexplorer.ui.dashboard.IDashboardController;
import com.fitnessexplorer.ui.rawdata.IRawDataController;
import com.fitnessexplorer.ui.rawdata.RawDataModelImpl;

/**
 * Created by David.Bickford on 5/25/2016.
 */
public class ModelFactory
{
    public static IModel getNewControllerModel(IFitnessRepository repo, IPreferencesRepository preferencesRepo, IController controller)
    {
        if(controller instanceof IDashboardController)
        {
            return new DashboardModelImpl(repo, preferencesRepo, (IDashboardController)controller);
        }
        else
        {
            return new RawDataModelImpl(repo, preferencesRepo, (IRawDataController)controller);
        }
    }
}
