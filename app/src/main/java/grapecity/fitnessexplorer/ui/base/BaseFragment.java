package grapecity.fitnessexplorer.ui.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.fitnessexplorer.ui.base.IModel;
import com.fitnessexplorer.ui.base.IView;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import grapecity.fitnessexplorer.MyApp;
import grapecity.fitnessexplorer.R;

public class BaseFragment<M extends IModel> extends Fragment implements IView
{
    private ImageView image;
    protected M model;

    @Override
    public void loadToolbar()
    {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        image = (ImageView) toolbar.findViewById(R.id.toolbarImage);

        if (!model.isGoogleFitEnabled())
        {
            image.setImageResource(R.drawable.fit_icon_disabled);
        }

        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                model.toggleDataSourceClicked();
            }
        });
    }

    @Override
    public void reload()
    {
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }

    ShowcaseView compileShowcaseView;

    @Override
    public void showTutorial()
    {
        compileShowcaseView = new ShowcaseView.Builder(getActivity())
                .withNewStyleShowcase()
                .setStyle(R.style.CustomShowcaseTheme2)
                .setTarget(new ViewTarget(image))
                .setContentTitle(R.string.tutorial_title)
                .setContentText(R.string.tutorial_description)
                .setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        model.tutorialOkClicked();

                        compileShowcaseView.hide();
                    }
                })
                .build();

        compileShowcaseView.show();
    }
}
