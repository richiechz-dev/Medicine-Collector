package com.example.recomed;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {

    //Funcion de barra
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    Button startProgress, btnCerrarSesion;
    TextView perfil, textview_email;
    EditText name, email;
    //Firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //Funcion para la barra
        progressBar = findViewById(R.id.progressBar);
        startProgress= findViewById(R.id.start_progress);

        //Funcion para Perfil
        //Variable de Perfil
        perfil = findViewById(R.id.textview_fullname);
        textview_email = findViewById(R.id.textview_email);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        textview_email.setText(user.getEmail());

        //barra de progreso
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