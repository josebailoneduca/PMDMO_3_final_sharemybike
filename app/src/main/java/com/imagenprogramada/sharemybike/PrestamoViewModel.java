package com.imagenprogramada.sharemybike;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel para los datos compartidos entre activity y fragmentos.
 * En este caso solo ese la fecha
 *
 * @author Jose Javier Bailon Ortiz
 */
public class PrestamoViewModel extends ViewModel {
    private String fecha;
    public void setFecha(String fecha){
        this.fecha=fecha;
    }
    public String getFecha() {
        return fecha;
    }
}
