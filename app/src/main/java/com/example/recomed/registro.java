package com.example.recomed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {

    Button regresaR;
    EditText txtNombre;
    EditText txtApellidoP;
    EditText txtApellidoM;
    EditText txtEmail;
    EditText txtpsw;
    Button btnregistro;

   private FirebaseAuth mAuth;
   private FirebaseFirestore db;
   private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        regresaR=(Button) findViewById(R.id.btnRegresarR);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellidoP = findViewById(R.id.txtApellidoP);
        txtApellidoM = findViewById(R.id.txtApellidoM);
        txtEmail = findViewById(R.id.txtEmail);
        txtpsw = findViewById(R.id.txtPsw);
        btnregistro = findViewById(R.id.btnregistro);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtNombre.getText().toString().trim();
                String lname1 = txtApellidoP.getText().toString().trim();
                String lname2 = txtApellidoM.getText().toString().trim();
                String email1 = txtEmail.getText().toString().trim();
                String psw = txtpsw.getText().toString().trim();

                if(name.isEmpty() && lname1.isEmpty() && lname2.isEmpty() && email1.isEmpty() && psw.isEmpty()){
                    Toast.makeText(registro.this, "Ingresa los datos", Toast.LENGTH_SHORT).show();
                }else{
                    createuser();
                }
            }
        });

        regresaR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registro.this, MainActivity.class);
                startActivity(i);
            }
        });



    }//end onCrate
    public void createuser(){

        String nombre = txtNombre.getText().toString();
        String apellidoP = txtApellidoP.getText().toString();
        String apellidoM = txtApellidoM.getText().toString();
        String email = txtEmail.getText().toString();
        String psw = txtpsw.getText().toString();

        if (TextUtils.isEmpty(nombre)){
            txtEmail.setError("ingrese un Nombre");
            txtEmail.requestFocus();
        }else if (TextUtils.isEmpty(apellidoP)){
            txtEmail.setError("ingrese un Apellido Paterno");
            txtEmail.requestFocus();
        }else if (TextUtils.isEmpty(apellidoM)){
            txtEmail.setError("ingrese un Apellido Materno");
            txtEmail.requestFocus();
        }else if (TextUtils.isEmpty(email)){
            txtEmail.setError("ingrese un Email");
            txtEmail.requestFocus();
        }else if (TextUtils.isEmpty(psw)){
            txtEmail.setError("ingrese una Contraseña");
            txtEmail.requestFocus();
        }else{

            mAuth.createUserWithEmailAndPassword(email, psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task){
                    if (task.isSuccessful()){
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users"). document(userID);

                        Map<String, Object> user=new HashMap<>();
                        user.put("nombre", nombre);
                        user.put("ApellidoP", apellidoP);
                        user.put("ApellidoM", apellidoM);
                        user.put("Email", email);
                        user.put("contraseña", psw);

                        documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("TAG", "onSuccess: Datos registrados"+ userID);
                            }
                        });
                        Toast.makeText(registro.this, "usuario registrado"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(registro.this, MainActivity.class));
                    }else{
                        Toast.makeText(registro.this, "usuario no registrado"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }//end Createuser
}