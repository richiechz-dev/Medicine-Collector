package com.example.recomed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class registro extends AppCompatActivity {

    Button regresaR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        regresaR=(Button) findViewById(R.id.btnRegresarR);

        regresaR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registro.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}