package com.example.stoplyingdown;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

public class SexPage extends AppCompatActivity {
    public Boolean IsMaleActive = false;
    public Boolean IsFemaleActive = false;
    public static final String APP_PREFERENCES_NAME = "userinfo";
    public static final String FIELD_NAME = "sex";
    private SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sex_page);

        Button male_button = findViewById(R.id.male_button);
        Button female_button = findViewById(R.id.female_button);
        Button step_info = findViewById(R.id.step_info);
        Button confirm_button = findViewById(R.id.confirm_button);

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsFemaleActive || IsMaleActive){
                    NextPage(view);
                }
            }
        });

        step_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendInfo();
            }
        });

        male_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsFemaleActive){
                    IsFemaleActive = false;
                    female_button.setBackgroundColor(Color.rgb(89, 89, 89));
                }
                IsMaleActive = true;
                male_button.setBackgroundColor(Color.rgb(102, 112, 255));
                confirm_button.setBackgroundColor(Color.rgb(48, 90, 202));

                System.out.println("You're male");
                SetSex("male", sPref);
//                NextPage(view);
            }
        });

        female_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsMaleActive){
                    IsMaleActive = false;
                    male_button.setBackgroundColor(Color.rgb(89, 89, 89));
                }
                IsFemaleActive = true;
                female_button.setBackgroundColor(Color.rgb(255, 102, 102));
                confirm_button.setBackgroundColor(Color.rgb(48, 90, 202));

                System.out.println("You're female");
                SetSex("female", sPref);
//                NextPage(view);
            }
        });
    }

    public void NextPage(View view){
        Intent intent = new Intent(this, HeightPage.class);
        System.out.println(sPref.getAll());
        startActivity(intent);
    }

    public void SetSex(String text, SharedPreferences sPref){
        SharedPreferences.Editor editor = sPref.edit();
         if (sPref.contains(FIELD_NAME)){
             editor.remove(FIELD_NAME);
             editor.apply();
         }
         editor.putString(FIELD_NAME, text);
         editor.apply();
    }

    private void SendInfo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Шаг №1")
                .setMessage("Ваш пол нам нужен для бла бла бла...")
                .setCancelable(true)
                .setPositiveButton("ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }
}