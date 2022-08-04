package com.example.recomed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class principal extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ConstraintLayout ticket, recomienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ticket = findViewById(R.id.aComprobante);
        recomienda = findViewById(R.id.aRecomienda);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(principal.this, vGenerarPdf.class));
            }
        });

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