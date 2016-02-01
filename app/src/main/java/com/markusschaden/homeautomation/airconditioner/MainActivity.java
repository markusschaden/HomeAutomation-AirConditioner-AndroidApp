package com.markusschaden.homeautomation.airconditioner;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.markusschaden.homeautomation.airconditioner.domain.CoolingEntry;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DayCoolingEntryFragment.OnListFragmentInteractionListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.pager)
    ViewPager viewPager;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        viewPager.setAdapter(new MainTabPager(getSupportFragmentManager(), MainActivity.this));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onListFragmentInteraction(CoolingEntry item) {

    }

    @Override
    public void onDayCoolingEntryClick(CoolingEntry mItem) {
        Intent intent = new Intent(this,DayCoolingEditActivity.class);
        intent.putExtra(DayCoolingEditActivity.ARG_COOLING_ENTRY, mItem);
        startActivity(intent);
        overridePendingTransition(R.anim.rotate_out,R.anim.rotate_in);
    }
}
