package com.example.bdexternas;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button usuario, mensajes;
    FragmentManager FM;
    FragmentTransaction FT;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.bt_usuarios);
        mensajes = findViewById(R.id.bt_mensaje);


        usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentUsuarios();
            }
        });

        mensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void fragmentUsuarios()
    {
        FM = getSupportFragmentManager();
        FT  = FM.beginTransaction();
        fragment = new FragmentUsuarios();
        FT.replace(R.id.fragment_container, fragment);
        FT.commit();
    }
}
