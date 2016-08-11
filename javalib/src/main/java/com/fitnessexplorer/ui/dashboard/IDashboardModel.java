package com.fitnessexplorer.ui.dashboard;

import com.fitnessexplorer.ui.base.IModel;

/**
 * Created by David.Bickford on 5/24/2016.
 */
public interface IDashboardModel extends IModel<IDashboardView>
{
    void viewReady(IDashboardView view);

    void browseFitnessDataClicked();
}
