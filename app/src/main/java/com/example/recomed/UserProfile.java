package com.example.recomed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

public class UserProfile extends AppCompatActivity {

    //Funcion de barra
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    Button startProgress, btnCerrarSesion, btnEliminarCuenta;
    TextView perfil, textview_email;
    TextInputEditText password;

    //Firebase
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private String idUser;

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
        //btnCerrarSesion
        btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion);
        btnEliminarCuenta = findViewById(R.id.eliminar_cuenta);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        idUser = mAuth.getCurrentUser().getUid();

        textview_email.setText(user.getEmail());


        //referenciar para obtener datos de las coleccines
        DocumentReference documentReference = fStore.collection("users").document(idUser);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot == null || mAuth.getCurrentUser() == null) return;
                perfil.setText(documentSnapshot.getString("nombre"));
            }
        });


                btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAuth.signOut();
                        Intent intent = new Intent(UserProfile.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        btnEliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),user.getUid());
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(UserProfile.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"No se puedo Eliminar",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
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