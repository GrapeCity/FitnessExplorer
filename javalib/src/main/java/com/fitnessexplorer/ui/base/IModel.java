package com.fitnessexplorer.ui.base;

/**
 * Created by David.Bickford on 5/25/2016.
 */
public interface IModel<V extends IView>
{
    void viewReady(V view);

    void toggleDataSourceClicked();

    boolean isGoogleFitEnabled();

    void tutorialOkClicked();
}
