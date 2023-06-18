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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String datenow = date.format(dateFormatter);
        sPref = getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String oldDate = sPref.getString("date", "99-99-9999");
        System.out.println(sPref.getAll());
        if (IsFirstMoreSecond(datenow, oldDate)){
            SetTime(datenow, sPref);
            System.out.println(sPref.getAll());
            sPref = getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            Integer activity = sPref.getInt("activity", 1);
            Integer mass = sPref.getInt("mass", 1);
            sPref = getContext().getSharedPreferences("activities", Context.MODE_PRIVATE);
            sPref.edit().clear().apply();

            if (activity == 1){
                //super active
                AddActivityMain(R.drawable.zaryadka,"Зарядка", "Выполнить утреннюю зарядку", false, true);
                AddActivityMain(R.drawable.walk,"Прогулка", "Нужно прогуляться 1 час", false, true);
                AddActivityMain(R.drawable.pushups,"Отжимания", "В сумме за день сделать 100 отжиманий", false, true);
                AddActivityMain(R.drawable.meditation,"Медитация", "Провести 20 минут, медетируя", false, true);
                AddActivityMain(R.drawable.planka,"Планка", "Продержаться в планке 5 миинут", false, true);

            } else if (activity == 2) {
                //medium active
                AddActivityMain(R.drawable.zaryadka,"Зарядка", "Выполнить утреннюю зарядку", false, true);
                AddActivityMain(R.drawable.walk,"Прогулка", "Нужно прогуляться 30 минут", false, true);
                AddActivityMain(R.drawable.pushups,"Отжимания", "В сумме за день сделать 70 отжиманий", false, true);
                AddActivityMain(R.drawable.meditation,"Медитация", "Провести 15 минут, медетируя", false, true);
                AddActivityMain(R.drawable.planka,"Планка", "Продержаться в планке 3 миинуты", false, true);
            }
            else{
                AddActivityMain(R.drawable.zaryadka,"Зарядка", "Выполнить утреннюю зарядку", false, true);
                AddActivityMain(R.drawable.walk,"Прогулка", "Нужно прогуляться 15 минут", false, true);
                AddActivityMain(R.drawable.pushups,"Отжимания", "В сумме за день сделать 40 отжиманий", false, true);
                AddActivityMain(R.drawable.meditation,"Медитация", "Провести 10 минут, медетируя", false, true);
                AddActivityMain(R.drawable.planka,"Планка", "Продержаться в планке 2 миинуты", false, true);
                //low active
            }

            if (mass == 1){
                //lose mass
                AddActivityMain(R.drawable.jumps,"Приседания с выпрыгиваниями", "Выполнить 4х20 приседаний с выпригываниями", false, true);
                AddActivityMain(R.drawable.skakalka,"Прыжки со скакалкой", "Время выполнения упражнения - 20 минут в день", false, true);
            }
            else if (mass == 2){
                //keep mass
            }
            else{
                //more mass
                AddActivityMain(R.drawable.bolgar,"Болгарские выпады", "Выполнить 4 подхода по 10-15 раз", false, true);
                AddActivityMain(R.drawable.prised,"Приседания", "Выполнить 4 подхода по 20 раз", false, true);

            }
        }


        sPref = getContext().getSharedPreferences("activities", Context.MODE_PRIVATE);
        System.out.println(sPref.getAll());
//        sPref.edit().clear().apply();
//        AddActivityMain(R.drawable.walk,"Прогулка", "Нужно прогуляться на пол часика чисто подышать воздухом", true, true);
//        AddActivityMain(R.drawable.baseline_aim,"Стрельба", "Нужно прогуляться на пол часика чисто подышать воздухом", true, true);
//        AddActivityMain(R.drawable.walk,"Прогулка", "Нужно прогуляться 1 час", true, true);
//        AddActivityMain(R.drawable.pushups,"Отжимания", "В сумме за день сделать 100 отжиманий", true, true);
//        AddActivityMain(R.drawable.meditation,"Медитация", "Провести 10 минут, медетируя", true, true);
        UploadActivities();

        RecyclerView recyclerView = inflatedView.findViewById(R.id.rv_list);
        Adapter adapter = new Adapter(getContext(), mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return inflatedView;
    }

    private void UploadActivities(){
        mlist.clear();
        SharedPreferences.Editor editor = sPref.edit();
        Map<String, ?> a = sPref.getAll();
        Integer size = a.size();
        for (int i = 1; i < size+1; i++){
            String item = sPref.getString(Integer.toString(i), "0");
            String[] itemArray = item.split("%");
            mlist.add(new item(Integer.parseInt(itemArray[0]), itemArray[1], itemArray[2], Boolean.parseBoolean(itemArray[3]), Boolean.parseBoolean(itemArray[4])));
        }
    }

    private String ToStringConverter(int background, String activityTitle, String description, Boolean isFinished, Boolean isStock){
        String result = Integer.toString(background)+"%"+activityTitle+"%"+description+"%"+Boolean.toString(isFinished)+"%"+Boolean.toString(isStock);
        return result;
    }

    private void AddActivityMain(int background, String activityTitle, String description, Boolean isFinished, Boolean isStock){
        String ActivityString = ToStringConverter(background, activityTitle, description, isFinished, isStock);
        SharedPreferences.Editor editor = sPref.edit();
        Integer index = sPref.getAll().size();
        editor.putString(Integer.toString(index+1), ActivityString);
        editor.apply();
        UploadActivities();
//        mlist.add(new item(background, activityTitle, description, isFinished));
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
                AddActivityMain(R.drawable.white_background, header.getText().toString(), description.getText().toString(), false, false);
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

    public boolean IsFirstMoreSecond(String date1, String date2){
        String[] date1_split = date1.split("-");
        String[] date2_split = date2.split("-");

        if (Integer.parseInt(date2_split[2]) > Integer.parseInt(date1_split[2]))
            return false;

        if (Integer.parseInt(date2_split[1]) > Integer.parseInt(date1_split[1]))
            return false;

        return Integer.parseInt(date2_split[0]) < Integer.parseInt(date1_split[0]);
    }

    public void SetTime(String date, SharedPreferences sPref){
        SharedPreferences.Editor editor = sPref.edit();
        if (sPref.contains("date")){
            editor.remove("date");
            editor.apply();
        }
        editor.putString("date", date);
        editor.apply();
    }
}