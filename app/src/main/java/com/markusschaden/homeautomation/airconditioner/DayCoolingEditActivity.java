package com.markusschaden.homeautomation.airconditioner;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.markusschaden.homeautomation.airconditioner.domain.CoolingEntry;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DayCoolingEditActivity extends AppCompatActivity  {

    public static final String ARG_COOLING_ENTRY = "cooling_entry";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_cooling_edit);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        CoolingEntry coolingEntry = (CoolingEntry)getIntent().getSerializableExtra(ARG_COOLING_ENTRY);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, DayCoolingEditFragment.newInstance(coolingEntry)).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
