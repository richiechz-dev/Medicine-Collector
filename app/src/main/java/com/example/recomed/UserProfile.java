package com.example.recomed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class UserProfile extends AppCompatActivity {

    //Funcion de barra
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //Funcion para la barra
        progressBar = findViewById(R.id.progressBar);
        startProgress= findViewById(R.id.start_progress);

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrentProgress = CurrentProgress + 2;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);
            }
        });
    }
}