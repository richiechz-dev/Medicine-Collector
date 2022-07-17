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
import com.google.firebase.firestore.FirebaseFirestore;


public class UserProfile extends AppCompatActivity {

    //Funcion de barra
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress, btnCerrarSesion, eliminar_cuenta;

    private TextView perfil, textview_email;
    private EditText name, email, password;

    //Firebase
    FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;
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
        name = findViewById(R.id.edittext_name);
        email = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_password);
        btnCerrarSesion= findViewById(R.id.btn_cerrar_sesion);
        eliminar_cuenta= findViewById(R.id.eliminar_cuenta);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        textview_email.setText(user.getEmail());
        password.setText(user.getUid());

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