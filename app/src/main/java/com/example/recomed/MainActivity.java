package com.example.recomed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button access;
    EditText psw, email;
    TextView registro;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registro=(TextView) findViewById(R.id.lblRegister);
        auth = FirebaseAuth.getInstance();


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        email = findViewById(R.id.txtEmail);
        psw = findViewById(R.id.txtPsw);
        access = findViewById(R.id.btnLogin);

        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString().trim();
                String psw1 = psw.getText().toString().trim();

                if(email.getText().toString().equals("abdiel@gmail.com") && psw.getText().toString().equals("admin123")) {
                    startActivity(new Intent(MainActivity.this, MainAdmin.class));
                }else if(email.getText().toString().equals("staff@gmail.com") && psw.getText().toString().equals("staff123")){
                        startActivity(new Intent(MainActivity.this, StaffActivity.class));
                }else {
                    if(email1.isEmpty() && psw1.isEmpty()){
                        Toast.makeText(MainActivity.this, "Ingresa los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        loginUser(email1, psw1);
                    }
                }
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, registro.class);
                startActivity(i);
            }
        });
    }

    private void loginUser(String email1, String psw1){
        auth.signInWithEmailAndPassword(email1, psw1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    ProcessLogin();
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "ERROR, email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "ERROR AL INICIAR SESIÓN", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*protected void onStart(){
        super.onStart();
        FirebaseUser usuario = auth.getCurrentUser();
        if (usuario != null){
            startActivity(new Intent(MainActivity.this, principal.class));
            finish();
        }
    }*/

    private void ProcessLogin() {
        SafetyNet.getClient(MainActivity.this).verifyWithRecaptcha("6Ldbv-8gAAAAAFzLQihyOui9130tTwR_abNGSKEl")
                .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse) {
                        String captchaToken = recaptchaTokenResponse.getTokenResult();

                        if(captchaToken != null){
                            if(!captchaToken.isEmpty()){
                                processLoginStep(captchaToken, email.getText().toString(), psw.getText().toString());

                                //seguimiento a otra interfaz
                                startActivity(new Intent(MainActivity.this, principal.class));
                            }else{
                                Toast.makeText(MainActivity.this, "Captcha Inválido", Toast.LENGTH_SHORT);
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Falla al cargar el Captcha", Toast.LENGTH_SHORT);
                    }
                });
    }

    private void processLoginStep(String token, String email, String psw) {
        Log.d("CAPTCHA TOKEN", ""+token);
    }
}