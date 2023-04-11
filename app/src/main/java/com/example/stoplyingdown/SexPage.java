package com.example.stoplyingdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SexPage extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "userinfo";
    public static final String FIELD_NAME = "sex";
    private SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sex_page);

        Button male_button = findViewById(R.id.male_button);
        Button female_button = findViewById(R.id.female_button);

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);

        male_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("You're male");
                SetSex("male", sPref);
                NextPage(view);
            }
        });

        female_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("You're female");
                SetSex("female", sPref);
                NextPage(view);
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
}