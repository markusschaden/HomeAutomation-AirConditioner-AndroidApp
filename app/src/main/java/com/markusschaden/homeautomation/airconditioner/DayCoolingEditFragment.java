package com.markusschaden.homeautomation.airconditioner;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.markusschaden.homeautomation.airconditioner.domain.CoolingEntry;

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

    private OnFragmentInteractionListener mListener;

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

        dayTextView.setText("Day: " + mCoolingEntry.toString());

        setStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    }
                }, mCoolingEntry.getStartTime().getHour(), mCoolingEntry.getStartTime().getMinute(), true);
                timePickerDialog.show();
            }
        });

        seekArc.setOnSeekArcChangeListener(new AirConSeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(AirConSeekArc seekArc, int value, boolean b) {
                seekArcProgress.setText("" + value + "°");
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
