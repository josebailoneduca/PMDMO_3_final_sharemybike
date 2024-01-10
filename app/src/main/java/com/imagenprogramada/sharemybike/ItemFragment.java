package com.imagenprogramada.sharemybike;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.imagenprogramada.sharemybike.bikes.BikesContent;

/**
 * Fragmento de lista de elementos. Comparte el viewModel con los otros fragmentos
 * y la BikeActivity
 *
 * @author Jose Javier Bailon Ortiz
 */
public class ItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    PrestamoViewModel viewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    /**
     * Configuracion del fragmento
     * @param columnCount
     * @return
     */
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //recoger el viewmodel
        viewModel = new ViewModelProvider(requireActivity()).get(PrestamoViewModel.class);

        //definir cantidad de columnas
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * Configuracion inicial del la vista. Configura el RecyclerView poniendo de adaptador un
     * MyItemRecyclerViewAdapter
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Configurar el recyclerView eligiendo layout lineal si solo hay una columna
        //como es el caso. En caso contrario eligiria gridlayout
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(BikesContent.ITEMS,this));
        }
        return view;
    }

    /**
     * Manda un email segun la ficha suministrada y la fecha almacenada en el viewmodel
     * @param ficha Ficha con los datos de la bicicleta
     */
    public void mandarEmail(BikesContent.Bike ficha){
        Log.i("Send email", "");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        String[]TO={ficha.getEmail()};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Someone wants to share your bike");
        String cuerpo= "Dear Mr/Mrs "+ficha.getOwner()+",\n\n" +
                "I'd like to use your bike at "+ficha.getLocation()+" ("+ficha.getCity()+")\n" +
                "for the following date: "+viewModel.getFecha()+" \n" +
                "Can you confirm its availability?\n" +
                "Kindest regards.";
        emailIntent.putExtra(Intent.EXTRA_TEXT, cuerpo);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this.getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }
}