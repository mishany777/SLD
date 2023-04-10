package com.example.stoplyingdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StarterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starter_page);

        Button startButton = findViewById(R.id.firstPageButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFirstRegistrationPage(view);
            }
        });
    }

    public void OpenFirstRegistrationPage(View v){
        Intent intent = new Intent(this, SexPage.class);
        startActivity(intent);
    }
}