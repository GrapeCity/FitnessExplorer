package com.fitnessexplorer.ui.rawdata;

import com.fitnessexplorer.ui.base.IModel;

/**
 * Created by David.Bickford on 5/24/2016.
 */
public interface IRawDataModel extends IModel<IRawDataView>
{
    void viewReady(IRawDataView view);
}
