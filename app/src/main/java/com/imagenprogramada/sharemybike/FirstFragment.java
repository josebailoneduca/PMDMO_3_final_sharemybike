package com.imagenprogramada.sharemybike;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.imagenprogramada.sharemybike.databinding.FragmentFirstBinding;

/**
 * Fragmento de seleccion de fecha
 *
 * @author Jose Javier Bailon Ortiz
 */
public class FirstFragment extends Fragment {

    /**
     * Binding de la vista
     */
    private FragmentFirstBinding binding;

    /**
     * Viewmodel compartido con otros fragmentos
     */
    PrestamoViewModel viewModel;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(PrestamoViewModel.class);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //capturar evento de seleccion de fecha
        ((CalendarView) binding.datePicker).setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String fecha = dayOfMonth + "/" + month + "/" + year;
            binding.tvTexto.setText("Date: " + fecha);
            viewModel.setFecha(fecha);
        });

        //capturar evento de boton next. Se pasa al siguiente fragmento(el de la lista)
        //solo si ya se ha elegido fecha
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewModel.getFecha() != null)
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.itemFragment);
                else
                    Toast.makeText(requireActivity(),"You must select a date",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}