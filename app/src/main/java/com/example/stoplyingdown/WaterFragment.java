package com.example.stoplyingdown;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String FIELD_NAME_WATER = "water";
    public static final String APP_PREFERENCES_NAME = "userinfo";

    public static final String FIELD_NAME_WEIGHT = "weight";

    public Integer alreadyDrink;

    private SharedPreferences sPref;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WaterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WaterFragment newInstance(String param1, String param2) {
        WaterFragment fragment = new WaterFragment();
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
        View inflatedView = inflater.inflate(R.layout.fragment_water, container, false);
        sPref = getContext().getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);

        ProgressBar progressBar = inflatedView.findViewById(R.id.progress);
        ImageButton plusButton = inflatedView.findViewById(R.id.plusButton);
        ImageButton minusButton = inflatedView.findViewById(R.id.minusButton);
        TextView cupsCounter = inflatedView.findViewById(R.id.cupsCounter);
        TextView infoText = inflatedView.findViewById(R.id.infoText);

        alreadyDrink = sPref.getInt(FIELD_NAME_WATER, 0);
        cupsCounter.setText(alreadyDrink.toString());

        Integer weight = (Integer) sPref.getInt(FIELD_NAME_WEIGHT, 1);
        Long needToDrink = Math.round(((weight / 30.0)*1000)/250);
        System.out.println(needToDrink);


        progressBar.setMax(Integer.parseInt(needToDrink.toString()));
        progressBar.setProgress(alreadyDrink);

        infoText.setText(String.format("Вам надо выпить %d стаканов воды, исходя и расчета 30 кг массы тела – 1 литр воды", needToDrink));

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alreadyDrink += 1;
                SetWater(alreadyDrink, sPref);
                cupsCounter.setText(alreadyDrink.toString());
                progressBar.setProgress(alreadyDrink);
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alreadyDrink > 0) {
                    alreadyDrink -= 1;
                    SetWater(alreadyDrink, sPref);
                    cupsCounter.setText(alreadyDrink.toString());
                    progressBar.setProgress(alreadyDrink);
                }
            }
        });



        return inflatedView;
    }

    public void SetWater(Integer waterCount, SharedPreferences sPref){
        SharedPreferences.Editor editor = sPref.edit();
        if (sPref.contains(FIELD_NAME_WATER)){
            editor.remove(FIELD_NAME_WATER);
            editor.apply();
        }
        editor.putInt(FIELD_NAME_WATER, waterCount);
        editor.apply();
    }
}