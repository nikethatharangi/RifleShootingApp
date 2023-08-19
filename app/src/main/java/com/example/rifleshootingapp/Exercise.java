package com.example.rifleshootingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Exercise extends AppCompatActivity {

    private Button btnBack;
    private Button btnGripexe;
    private Button btnArmexe;
    private Button btnBreathexe;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        btnBack = findViewById(R.id.btnBackOne);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenHome();
            }
        });

        btnGripexe = findViewById(R.id.btnGripExe);
        btnGripexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGripExe();
            }
        });

        btnArmexe = findViewById(R.id.btnArmExe);
        btnArmexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenArmExe();
            }
        });

        btnBreathexe = findViewById(R.id.btnBreathExe);
        btnBreathexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenBreathExe();
            }
        });
    }


    public void OpenArmExe(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=8MXxO0J2qPQ"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }

    public void OpenGripExe(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wPxRWTb2VkY"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }

    public void OpenBreathExe(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=NnVkd-Rprvg"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }

    public void OpenHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}