package grapecity.fitnessexplorer.ui.base;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.fitnessexplorer.ui.base.IView;

public class BaseFragment extends Fragment implements IView
{
    @Override
    public void reload()
    {
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }
}
