package com.example.stoplyingdown;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HeightPage extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "userinfo";
    public static final String FIELD_NAME = "height";
    private SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.height_page);

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();

        EditText heightFrame = findViewById(R.id.height);
        Button nextPageButton = findViewById(R.id.nextPageButtonHeight);
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String height_string = heightFrame.getText().toString();
                if (height_string.equals("")){
                    SendAlert();
                    return;
                }

                Integer height = Integer.parseInt(height_string);
                if (height >= 67 && height <= 272 && height != 0) {
                    SetHeight(height, sPref);
                    NextPage(view);
                }
                else {
                    SendAlert();
                    return;
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
        System.out.println(sPref.getAll());
        startActivity(intent);
    }

    public void SetHeight(Integer height, SharedPreferences sPref){
        SharedPreferences.Editor editor = sPref.edit();
        if (sPref.contains(FIELD_NAME)){
            editor.remove(FIELD_NAME);
            editor.apply();
        }
        editor.putInt(FIELD_NAME, height);
        editor.apply();
    }
}