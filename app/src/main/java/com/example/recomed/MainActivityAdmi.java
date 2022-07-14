package com.example.recomed;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.recomed.fragmentos_admi.borrar_admi;
import com.example.recomed.fragmentos_admi.inicio_admi;
import com.example.recomed.fragmentos_admi.modificacion_admi;
import com.example.recomed.fragmentos_admi.mostrar_admi;
import com.google.android.material.navigation.NavigationView;

public class MainActivityAdmi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admi);

        Toolbar toolbar=findViewById(R.id.toolbarA);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout_A);
        NavigationView navigationView=findViewById(R.id.navigation_view_A);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_A,
                    new inicio_admi()).commit();
            navigationView.setCheckedItem(R.id.InicioAdmi);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.InicioAdmi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_A,
                        new inicio_admi()).commit();
                break;
            case R.id.ModificarAdmi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_A,
                        new modificacion_admi()).commit();
                break;
            case R.id.MostrarAdmi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_A,
                        new mostrar_admi()).commit();
                break;
            case R.id.BorrarAdmi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_A,
                        new borrar_admi()).commit();
                break;
            case R.id.Salir:
                Toast.makeText(this, "Cerraste sesion", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
