package com.example.recomed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class principal extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private Button btn_vista;
    private Button btn_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btn_vista = findViewById(R.id.button);
        btn_admin = findViewById(R.id.admin);

        btn_vista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principal.this, UserProfile.class);
                startActivity(i);
            }
        });

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(principal.this,  vGenerarPdf.class);
                startActivity(i);
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), UserProfile.class));
                        overridePendingTransition(0, 0);
                        return;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), principal.class));
                        overridePendingTransition(0, 0);
                        return;
                    case R.id.dona:
                        startActivity(new Intent(getApplicationContext(), vDonacion.class));
                        overridePendingTransition(0, 0);
                        return;
                }
            }
        });


    }

}