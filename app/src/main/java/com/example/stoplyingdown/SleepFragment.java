package com.example.stoplyingdown;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.lang.reflect.Array;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SleepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SleepFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Dialog dialog;
    private TextView fellasleep;
    private TextView wakeup;
    private SharedPreferences sPref;
    private SharedPreferences.Editor editor;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SleepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SleepFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SleepFragment newInstance(String param1, String param2) {
        SleepFragment fragment = new SleepFragment();
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
        View inflatedView = inflater.inflate(R.layout.fragment_sleep, container, false);
        fellasleep = inflatedView.findViewById(R.id.fallasleep);
        wakeup = inflatedView.findViewById(R.id.wakeup);
        sPref = getContext().getSharedPreferences("sleep_time", Context.MODE_PRIVATE);
        editor  = sPref.edit();
        UpdateTime(sPref.getInt("hours_sleep", 0), sPref.getInt("minutes_sleep", 0));
        dialog = new Dialog(getContext());

        fellasleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

        wakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
        return inflatedView;
    }

    private void showCustomDialog() {
        dialog.setContentView(R.layout.sleep_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button cancel = dialog.findViewById(R.id.cancel);
        Button accept = dialog.findViewById(R.id.accept);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText time = dialog.findViewById(R.id.editTextTime2);
                EditText needToSleep = dialog.findViewById(R.id.needToSleep);
                String timetext = time.getText().toString();
                String needToSleepText = needToSleep.getText().toString();
                if (!timetext.contains(":"))
                    return;
                String[] array = timetext.split(":");
                Integer hours = Integer.parseInt(array[0]);
                Integer minutes = Integer.parseInt(array[1]);

                if (!needToSleepText.contains(":"))
                    return;
                String[] array_sleep = needToSleepText.split(":");
                Integer hours_sleep = Integer.parseInt(array_sleep[0]);
                Integer minutes_sleep = Integer.parseInt(array_sleep[1]);

                System.out.println(hours);
                System.out.println(minutes);
                if ((hours <= 24 && hours >= 0 && minutes >= 0 && minutes < 60) &&
                        (hours_sleep <= 24 && hours_sleep >= 0 && minutes_sleep >= 0 && minutes_sleep < 60)) {
                    editor.putInt("hours", hours);
                    editor.putInt("minutes", minutes);
                    editor.putInt("hours_sleep", hours_sleep);
                    editor.putInt("minutes_sleep", minutes_sleep);
                    editor.apply();
                    System.out.println("done");
                }
                UpdateTime(hours_sleep, minutes_sleep);
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void UpdateTime(Integer needToSleepHours, Integer needToSleepMinutes){
        Integer hours = sPref.getInt("hours", 0);
        Integer minutes = sPref.getInt("minutes", 0);
        System.out.println(hours);
        System.out.println(minutes);
        System.out.println(sPref.getAll());
        wakeup.setText((hours.toString().length() == 1 ? "0"+hours.toString() : hours.toString()) + ":" +
                (minutes.toString().length() == 1 ? "0"+minutes.toString() : minutes.toString()));
        Integer fell_hours = hours - needToSleepHours;
        fell_hours = fell_hours < 0 ? 24+fell_hours : fell_hours;
        Integer fell_minutes = minutes - needToSleepMinutes;
        if (fell_minutes < 0){
            fell_hours -= 1;
        }
        fell_minutes = fell_minutes < 0 ? 60+fell_minutes : fell_minutes;
        fellasleep.setText((fell_hours.toString().length() == 1 ? "0"+fell_hours.toString() : fell_hours.toString()) + ":" +
                (fell_minutes.toString().length() == 1 ? "0"+fell_minutes.toString() : fell_minutes.toString()));
    }
}