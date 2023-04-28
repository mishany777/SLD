package com.example.stoplyingdown;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AimFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String APP_PREFERENCES_NAME = "userinfo";
    public static final String FIELD_NAME_ACTIVITY = "activity";
    public static final String FIELD_NAME_MASS = "mass";

    private SharedPreferences sPref;

    private String mParam1;
    private String mParam2;

    public AimFragment() {
        // Required empty public constructor
    }


    public static AimFragment newInstance(String param1, String param2) {
        AimFragment fragment = new AimFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_aim, container, false);

        sPref = getContext().getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);

        Integer activityValue = sPref.getInt(FIELD_NAME_ACTIVITY, 1);

        SwitchCompat hightActiveSwitch = inflatedView.findViewById(R.id.hightActive);
        SwitchCompat activeSwitch = inflatedView.findViewById(R.id.active);
        SwitchCompat standUpSwitch = inflatedView.findViewById(R.id.stand_up);

        SwitchCompat[] ActivitySwitches = new SwitchCompat[3];
        ActivitySwitches[0]= hightActiveSwitch;
        ActivitySwitches[1]= activeSwitch;
        ActivitySwitches[2]= standUpSwitch;

        ActivitySwitches[activityValue-1].toggle();
        hightActiveSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activeSwitch.isChecked())
                    activeSwitch.toggle();
                if (standUpSwitch.isChecked())
                    standUpSwitch.toggle();
                SetType(FIELD_NAME_ACTIVITY, "1", sPref);
            }
        });

        activeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hightActiveSwitch.isChecked())
                    hightActiveSwitch.toggle();
                if (standUpSwitch.isChecked())
                    standUpSwitch.toggle();
                SetType(FIELD_NAME_ACTIVITY, "2", sPref);
            }
        });

        standUpSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activeSwitch.isChecked())
                    activeSwitch.toggle();
                if (hightActiveSwitch.isChecked())
                    hightActiveSwitch.toggle();
                SetType(FIELD_NAME_ACTIVITY,"3", sPref);
            }
        });

        SwitchCompat loseSwitch = inflatedView.findViewById(R.id.lose);
        SwitchCompat keepSwitch = inflatedView.findViewById(R.id.keep);
        SwitchCompat moreSwitch = inflatedView.findViewById(R.id.more);

        SwitchCompat[] MassSwitches = new SwitchCompat[3];
        MassSwitches[0]= loseSwitch;
        MassSwitches[1]= keepSwitch;
        MassSwitches[2]= moreSwitch;

        loseSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (keepSwitch.isChecked())
                    keepSwitch.toggle();
                if (moreSwitch.isChecked())
                    moreSwitch.toggle();
                SetType(FIELD_NAME_MASS, "1", sPref);
            }
        });

        keepSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loseSwitch.isChecked())
                    loseSwitch.toggle();
                if (moreSwitch.isChecked())
                    moreSwitch.toggle();
                SetType(FIELD_NAME_MASS, "2", sPref);
            }
        });

        moreSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loseSwitch.isChecked())
                    loseSwitch.toggle();
                if (keepSwitch.isChecked())
                    keepSwitch.toggle();
                SetType(FIELD_NAME_MASS, "3", sPref);
            }
        });

        return inflatedView;
    }

    public void SetType(String fieldName, String typeString, SharedPreferences sPref) {
        SharedPreferences.Editor editor = sPref.edit();
        Integer type = Integer.parseInt(typeString);
        if (sPref.contains(fieldName)) {
            editor.remove(fieldName);
            editor.apply();
        }
        editor.putInt(fieldName, type);
        editor.apply();
        System.out.println(sPref.getAll());
    }
}