package com.example.stoplyingdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class new_sex extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "userinfo";
    public static final String FIELD_NAME = "sex";

    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_sex);

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);

        Button male_button = findViewById(R.id.male_button);
        Button female_button = findViewById(R.id.female_button);

        male_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSex("male", sPref);
                NextPage(view);
            }
        });

        female_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSex("female", sPref);
                NextPage(view);
            }
        });
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

    public void NextPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        System.out.println(sPref.getAll());
        startActivity(intent);
    }
}