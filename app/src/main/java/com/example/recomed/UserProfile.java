package com.example.recomed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserProfile extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    //Funcion de barra
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    Button startProgress, btnCerrarSesion, btnEliminarCuenta, btnGuardar;
    ImageView btnmaps;
    TextView perfil, textview_email;
    TextInputLayout perfilInput, emailInput;
    EditText perfilN, emailN;
    Activity activity;

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

        perfilN = findViewById(R.id.txtNombre);
        emailN = findViewById(R.id.txtEmail);

        //btnCerrarSesion
        btnCerrarSesion = findViewById(R.id.btn_cerrar_sesion);
        btnEliminarCuenta = findViewById(R.id.eliminar_cuenta);
        btnGuardar = findViewById(R.id.btn_update);
        btnmaps = findViewById(R.id.btnMaps);



        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        idUser = mAuth.getCurrentUser().getUid();

        textview_email.setText(user.getEmail());

       btnmaps.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(UserProfile.this, Maps.class);
               startActivity(intent);
           }
       });


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
                deleteuser(idUser);
                mAuth.signOut();
                Intent i = new Intent(UserProfile.this, MainActivity.class);
                startActivity(i);
            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = perfilN.getText().toString();
                String email = emailN.getText().toString();

                idUser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                DocumentReference documentReference = fStore.collection("users"). document(idUser);

                Map<String,Object> user=new HashMap<>();
                user.put("nombre", nombre);
                user.put("contraseña", email);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: Datos registrados"+idUser);
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
    private void deleteuser(String idUser) {
        fStore.collection("users").document(idUser).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado Corectamente", Toast.LENGTH_SHORT).show();
            }
        });
    }


}