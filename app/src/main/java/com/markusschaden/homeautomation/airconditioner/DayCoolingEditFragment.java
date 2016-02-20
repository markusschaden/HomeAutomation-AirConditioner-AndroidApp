package com.markusschaden.homeautomation.airconditioner;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.markusschaden.homeautomation.airconditioner.animation.FlipAnimation;
import com.markusschaden.homeautomation.airconditioner.dal.DataService;
import com.markusschaden.homeautomation.airconditioner.domain.CoolingEntry;
import com.markusschaden.homeautomation.airconditioner.domain.Time;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DayCoolingEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayCoolingEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class DayCoolingEditFragment extends Fragment {
    private static final String ARG_COOLINGENTRY = "cooling_entry";

    private CoolingEntry mCoolingEntry;

    //private CoolingEntryChange mListener;

    @Bind(R.id.day)
    TextView dayTextView;

    @Bind(R.id.setStartTime)
    Button setStartTime;

    @Bind(R.id.setStopTime)
    Button setStopTime;

    @Bind(R.id.seekArc)
    AirConSeekArc seekArc;

    @Bind(R.id.seekArcProgress)
    TextView seekArcProgress;

    @Bind(R.id.startTime)
    TextView startTime;

    @Bind(R.id.stopTime)
    TextView stopTime;

    @Bind(R.id.switcher)
    Switch switcher;

    @Bind(R.id.switcher2)
    Switch switcher2;

    @Bind(R.id.rootCardLayout)
    RelativeLayout rootLayout;

    @Bind(R.id.cardForegroundFace)
    CardView foregroundCard;

    @Bind(R.id.cardBackgroundFace)
    CardView backgroundCard;

    public static DayCoolingEditFragment newInstance(CoolingEntry coolingEntry) {
        DayCoolingEditFragment fragment = new DayCoolingEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_COOLINGENTRY, coolingEntry);
        fragment.setArguments(args);
        return fragment;
    }
    public DayCoolingEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCoolingEntry = (CoolingEntry)getArguments().getSerializable(ARG_COOLINGENTRY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_day_cooling_edit, container, false);

        ButterKnife.bind(this, rootView);

        if(mCoolingEntry.isEnabled()) {
            foregroundCard.setVisibility(View.VISIBLE);
            backgroundCard.setVisibility(View.GONE);
        } else {
            foregroundCard.setVisibility(View.GONE);
            backgroundCard.setVisibility(View.VISIBLE);
        }

        dayTextView.setText("Day: " + mCoolingEntry.getDay().toString());
        startTime.setText(getString(R.string.startTime) + " " + mCoolingEntry.getStartTime().formatted());
        stopTime.setText(getString(R.string.stopTime) + " " + mCoolingEntry.getStopTime().formatted());
        switcher.setChecked(mCoolingEntry.isEnabled());
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCoolingEntry.setEnabled(isChecked);
                switcher.setChecked(mCoolingEntry.isEnabled());
                switcher2.setChecked(mCoolingEntry.isEnabled());
                flipCard();
                saveChange(mCoolingEntry);
            }
        });

        switcher2.setChecked(mCoolingEntry.isEnabled());
        switcher2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCoolingEntry.setEnabled(isChecked);
                switcher.setChecked(mCoolingEntry.isEnabled());
                switcher2.setChecked(mCoolingEntry.isEnabled());
                flipCard();
                saveChange(mCoolingEntry);

            }
        });

        setStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mCoolingEntry.setStartTime(new Time(hourOfDay, minute));
                        startTime.setText(getString(R.string.startTime) + " " + mCoolingEntry.getStartTime().formatted());

                        saveChange(mCoolingEntry);
                    }
                }, mCoolingEntry.getStartTime().getHour(), mCoolingEntry.getStartTime().getMinute(), true);
                timePickerDialog.show();
            }
        });

        setStopTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mCoolingEntry.setStopTime(new Time(hourOfDay, minute));
                        stopTime.setText(getString(R.string.stopTime) + " " + mCoolingEntry.getStopTime().formatted());

                        saveChange(mCoolingEntry);
                    }
                }, mCoolingEntry.getStopTime().getHour(), mCoolingEntry.getStopTime().getMinute(), true);
                timePickerDialog.show();
            }
        });

        seekArcProgress.setText("" + mCoolingEntry.getTemperature() + "°");
        seekArc.setProgress(mCoolingEntry.getTemperature());
        seekArc.setOnSeekArcChangeListener(new AirConSeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(AirConSeekArc seekArc, int value, boolean b) {
                seekArcProgress.setText("" + value + "°");
                saveChange(mCoolingEntry);
            }

            @Override
            public void onStartTrackingTouch(AirConSeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(AirConSeekArc seekArc) {

            }
        });

        return rootView;
    }

    private void saveChange(CoolingEntry mCoolingEntry) {
        DataService.getData().get(mCoolingEntry.getDay()).add(mCoolingEntry);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof CoolingEntryChange) {
            mListener = (CoolingEntryChange) context;
        } else {
            //throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    private void flipCard()
    {
        FlipAnimation flipAnimation = new FlipAnimation(foregroundCard, backgroundCard);

        if (foregroundCard.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        rootLayout.startAnimation(flipAnimation);
    }
}
