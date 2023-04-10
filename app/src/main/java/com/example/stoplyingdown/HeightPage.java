package com.example.stoplyingdown;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HeightPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.height_page);

        EditText heightFrame = findViewById(R.id.height);
        Button nextPageButton = findViewById(R.id.nextPageButtonHeight);
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer height = Integer.parseInt(heightFrame.getText().toString());
                if (height >= 67 && height <= 272) {
                    NextPage(view);
                }
                else {
                    SendAlert();
                }
            }
        });
    }

    private void SendAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ошибка!")
                        .setMessage("Неправильно введен рост")
                        .setCancelable(true)
                .setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    public void NextPage(View view){
        Intent intent = new Intent(this, StarterPage.class);
        startActivity(intent);
    }
}