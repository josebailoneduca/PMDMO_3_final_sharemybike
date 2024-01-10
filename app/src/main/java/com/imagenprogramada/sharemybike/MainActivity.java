package com.imagenprogramada.sharemybike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.imagenprogramada.sharemybike.databinding.ActivityMainBinding;

/**
 * Actividad inicial. Muestra la portada y gestiona el boton de login.
 * Al pulsar el boton de login se lanza la actividad BikeActivity
 *
 * @author Jose Javier Bailon Ortiz
 */
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(v -> {
            Intent intentLogin =new Intent(this,com.imagenprogramada.sharemybike.BikeActivity.class);
            startActivity(intentLogin);
            });

    }
}