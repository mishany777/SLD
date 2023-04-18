package com.example.stoplyingdown;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LogInPage extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "userinfo";
    public static final String FIELD_NAME_USERNAME = "username";
    public static final String FIELD_NAME_HEIGHT = "height";
    public static final String FIELD_NAME_WEIGHT = "weight";
    private SharedPreferences sPref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_page);

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();

        EditText username = findViewById(R.id.usernameField);
        EditText height = findViewById(R.id.heightField);
        EditText weight = findViewById(R.id.weightField);
        Button nextPageButton = findViewById(R.id.nextPageButtonLogin);

        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_string = username.getText().toString();
                String height_string = height.getText().toString();
                String weight_string = weight.getText().toString();

                if (SetUsername(username_string, sPref) && SetHeight(height_string, sPref) && SetWeight(weight_string, sPref))
                    NextPage(view);
            }
        });
    }

    private void SendAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ошибка!")
                .setMessage("Некорректно введены данные!")
                .setCancelable(true)
                .setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    public void NextPage(View view){
        Intent intent = new Intent(this, new_sex.class);
        System.out.println(sPref.getAll());
        startActivity(intent);
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
            SendAlert();
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
            SendAlert();
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
            SendAlert();
            return false;
        }
    }
}
