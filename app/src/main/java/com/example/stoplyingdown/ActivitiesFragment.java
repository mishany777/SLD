package com.example.stoplyingdown;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.app.Dialog;
import android.os.Bundle;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SharedPreferences sPref;

    List<item> mlist;
    Dialog dialog;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment activitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
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
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_activities, container, false);
        dialog = new Dialog(getContext());
        ImageButton addActivity = inflatedView.findViewById(R.id.addActivity);
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddActivityDialog();
            }
        });

        Window w = getActivity().getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);



        mlist = new ArrayList<>();

//        mlist.add(new item(R.drawable.walk,"Прогулка", "Нужно прогуляться на пол часика чисто подышать воздухом", false));
//        mlist.add(new item(R.drawable.water_glass,"Отжимания", "I gonna put your ass out", false));
//        mlist.add(new item(R.drawable.moon,"Глазная разминочка", "I gonna put your ass out", false));

        sPref = getContext().getSharedPreferences("activities", Context.MODE_PRIVATE);
//        sPref.edit().clear().apply();
//        AddActivityMain(R.drawable.walk,"Прогулка", "Нужно прогуляться на пол часика чисто подышать воздухом", true);
//        AddActivityMain(R.drawable.baseline_aim,"Стрельба", "Нужно прогуляться на пол часика чисто подышать воздухом", true);
        UploadActivities();

        RecyclerView recyclerView = inflatedView.findViewById(R.id.rv_list);
        Adapter adapter = new Adapter(getContext(), mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return inflatedView;
    }

    private void UploadActivities(){
        SharedPreferences.Editor editor = sPref.edit();
        Map<String, ?> a = sPref.getAll();
        Integer size = a.size();
        for (int i = 1; i < size+1; i++){
            String item = sPref.getString(Integer.toString(i), "0");
            String[] itemArray = item.split("%");
            mlist.add(new item(Integer.parseInt(itemArray[0]), itemArray[1], itemArray[2], Boolean.parseBoolean(itemArray[3])));
        }
    }

    private String ToStringConverter(int background, String activityTitle, String description, Boolean isFinished){
        String result = Integer.toString(background)+"%"+activityTitle+"%"+description+"%"+Boolean.toString(isFinished);
        return result;
    }

//    private Array FromStringConverter(String){
//    }

    private void AddActivityMain(int background, String activityTitle, String description, Boolean isFinished){
        String ActivityString = ToStringConverter(background, activityTitle, description, isFinished);
        SharedPreferences.Editor editor = sPref.edit();
        Integer index = sPref.getAll().size();
        editor.putString(Integer.toString(index+1), ActivityString);
        editor.apply();
        UploadActivities();
    }

    private void AddActivityDialog(){
        dialog.setContentView(R.layout.add_activity_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button accept = dialog.findViewById(R.id.accept_button_activity);
        Button cancel = dialog.findViewById(R.id.cancel_button_activity);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText header = dialog.findViewById(R.id.activity_header);
                EditText description = dialog.findViewById(R.id.activity_description);
                AddActivityMain(R.drawable.white_background, header.getText().toString(), description.getText().toString(), false);
                dialog.cancel();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}