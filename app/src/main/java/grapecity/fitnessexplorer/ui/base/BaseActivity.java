package grapecity.fitnessexplorer.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitnessexplorer.services.repo.preferences.IPreferencesRepository;

import grapecity.fitnessexplorer.MyApp;
import grapecity.fitnessexplorer.R;

public class BaseActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        View.inflate(this, R.layout.view_toolbar, toolbar);

        setSupportActionBar(toolbar);
    }
}