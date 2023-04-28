package com.example.stoplyingdown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String APP_PREFERENCES_NAME = "userinfo";
    public static final String FIELD_NAME_USERNAME = "username";
    public static final String FIELD_NAME_HEIGHT = "height";
    public static final String FIELD_NAME_WEIGHT = "weight";
    public static final String FIELD_NAME_SEX = "sex";

    private String newHeight = "";
    private String newWeight = "";
    private String newUsername = "";
    private SharedPreferences sPref;


    private static Boolean isEdit = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View inflatedView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inflate the layout for this fragment
        sPref = getContext().getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);


        TextView height_field = inflatedView.findViewById(R.id.height_number);
        TextView weight_field = inflatedView.findViewById(R.id.weight_number);
        TextView sex_field = inflatedView.findViewById(R.id.sex_number);
        TextView username_field = inflatedView.findViewById(R.id.usernameField);


        ImageView height_edit = inflatedView.findViewById(R.id.edit_button_height);
        ImageView weight_edit = inflatedView.findViewById(R.id.edit_button_weight);
        ImageView username_edit = inflatedView.findViewById(R.id.edit_button_username);

        height_edit.setVisibility(View.INVISIBLE);
        weight_edit.setVisibility(View.INVISIBLE);
        username_edit.setVisibility(View.INVISIBLE);

        height_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                    alert.setTitle("Новый рост");

                    final EditText inputField = new EditText(getContext());
                    inputField.setInputType(2);
                    alert.setView(inputField);

                    alert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            newHeight = inputField.getText().toString();
                            System.out.println("--"+newHeight);
                        }
                    });

                    alert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
                }
            }
        });

        weight_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                    alert.setTitle("Новый вес");

                    final EditText inputField = new EditText(getContext());
                    inputField.setInputType(2);
                    alert.setView(inputField);

                    alert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            newWeight = inputField.getText().toString();
                            System.out.println("--"+newHeight);
                        }
                    });

                    alert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
                }
            }
        });

        username_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                    alert.setTitle("Новое имя");

                    final EditText inputField = new EditText(getContext());
                    alert.setView(inputField);

                    alert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            newUsername = inputField.getText().toString();
                            System.out.println("--"+newHeight);
                        }
                    });

                    alert.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
                }
            }
        });


        Button editbutton = inflatedView.findViewById(R.id.edit_button);

        Integer height = sPref.getInt(FIELD_NAME_HEIGHT, 1);
        Integer weight = sPref.getInt(FIELD_NAME_WEIGHT, 1);
        String username = sPref.getString(FIELD_NAME_USERNAME, "User");
        String sex = sPref.getString(FIELD_NAME_SEX, "none");


        height_field.setText(height.toString());
        weight_field.setText(weight.toString());
        username_field.setText(username);
        sex_field.setText(sex);


        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    editbutton.setText("Редактировать");
                    height_edit.setVisibility(View.INVISIBLE);
                    weight_edit.setVisibility(View.INVISIBLE);
                    username_edit.setVisibility(View.INVISIBLE);

                    if (newHeight != ""){
                        if (SetHeight(newHeight, sPref)){
                            Integer height = sPref.getInt(FIELD_NAME_HEIGHT, 1);
                            height_field.setText(height.toString());
                        }
                    }

                    if (newWeight != ""){
                        if (SetWeight(newWeight, sPref)){
                            Integer weight = sPref.getInt(FIELD_NAME_WEIGHT, 1);
                            weight_field.setText(weight.toString());
                        }
                    }

                    if (newUsername != ""){
                        if (SetUsername(newUsername, sPref)){
                            String username = sPref.getString(FIELD_NAME_USERNAME, "User");
                            username_field.setText(username);
                        }
                    }
                    System.out.println(sPref.getAll());

                    isEdit = false;
                } else {
                    editbutton.setText("Сохранить ✓︎");
                    height_edit.setVisibility(View.VISIBLE);
                    weight_edit.setVisibility(View.VISIBLE);
                    username_edit.setVisibility(View.VISIBLE);

                    isEdit = true;
                }
            }
        });

        height_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("REDACT MEEE");
            }
        });

        return inflatedView;
    }

    public boolean SetHeight(String height_string, SharedPreferences sPref){
        SharedPreferences.Editor editor = sPref.edit();
        Integer height = Integer.parseInt(height_string);
        if (height >= 67 && height <= 272 && height != 0) {
            if (sPref.contains(FIELD_NAME_HEIGHT)){
                editor.remove(FIELD_NAME_HEIGHT);
                editor.apply();
            }
            editor.putInt(FIELD_NAME_HEIGHT, height);
            editor.apply();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean SetWeight(String weight_string, SharedPreferences sPref){
        SharedPreferences.Editor editor = sPref.edit();
        Integer weight = Integer.parseInt(weight_string);

        if (weight >= 30 && weight <= 250 && weight != 0) {
            if (sPref.contains(FIELD_NAME_WEIGHT)){
                editor.remove(FIELD_NAME_WEIGHT);
                editor.apply();
            }
            editor.putInt(FIELD_NAME_WEIGHT, weight);
            editor.apply();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean SetUsername(String username, SharedPreferences sPref){
        SharedPreferences.Editor editor = sPref.edit();

        if (username.length() > 0) {
            if (sPref.contains(FIELD_NAME_USERNAME)){
                editor.remove(FIELD_NAME_USERNAME);
                editor.apply();
            }
            editor.putString(FIELD_NAME_USERNAME, username);
            editor.apply();
            return true;
        }
        else {
            return false;
        }
    }

}