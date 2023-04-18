package com.example.stoplyingdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HiPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hi_page);

        ImageView startButton = findViewById(R.id.buttonImageView);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFirstRegistrationPage(view);
            }
        });
    }

    public void OpenFirstRegistrationPage(View v){
        Intent intent = new Intent(this, LogInPage.class);
        startActivity(intent);
    }
}