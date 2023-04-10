package com.example.stoplyingdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SexPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sex_page);

        Button male_button = findViewById(R.id.male_button);
        Button female_button = findViewById(R.id.female_button);

        male_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("You're male");
                NextPage(view);
            }
        });

        female_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("You're female");
                NextPage(view);
            }
        });
    }

    public void NextPage(View view){
        Intent intent = new Intent(this, HeightPage.class);
        startActivity(intent);
    }
}