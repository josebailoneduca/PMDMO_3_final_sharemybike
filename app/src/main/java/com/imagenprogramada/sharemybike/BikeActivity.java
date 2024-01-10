package com.imagenprogramada.sharemybike;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.imagenprogramada.sharemybike.bikes.BikesContent;
import com.imagenprogramada.sharemybike.databinding.ActivityBikeBinding;

/**
 * Actividad que navega por los fragmentos de seleccion de fecha y listado de bicicletas.
 * Contiene los datos compartidos entre fragmentos en el viewmodel PrestamoViewModel
 *
 * @author Jose Javier Bailon Ortiz
 */
public class BikeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityBikeBinding binding;

    private PrestamoViewModel prestamoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //carga de bicicletas desde json
        BikesContent.loadBikesFromJSON(this);
        //binding de la vista
        binding = ActivityBikeBinding.inflate(getLayoutInflater());

        //viewmodel donde se compartira la fecha entre fragmentos
        prestamoViewModel = new ViewModelProvider(this).get(PrestamoViewModel.class);
        //set layout
        setContentView(binding.getRoot());
        //configura actionbar y navegacion
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bike);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bike);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}