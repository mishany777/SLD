package com.example.stoplyingdown;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HiPage extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "userinfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sPref = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        if (sPref.contains("registered")) { SkipRegistrationPage(); }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hi_page);

        ImageView startButton = findViewById(R.id.buttonImageView);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                OpenFirstRegistrationPage(view);
            }
        });
    }

    public void OpenFirstRegistrationPage(View v){
        Intent intent = new Intent(this, LogInPage.class);
        startActivity(intent);
    }

    public void SkipRegistrationPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}