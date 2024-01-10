package com.imagenprogramada.sharemybike;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.imagenprogramada.sharemybike.bikes.BikesContent;
import com.imagenprogramada.sharemybike.databinding.FragmentItemBinding;

import java.util.List;

/**
 * Adaptador para la lista de bicicletas
 *
 * @author Jose Javier Bailon Ortiz
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<BikesContent.Bike> mValues;
    /**
     * Referencia al fragmento para tener acceso a la funcion de mandar email
     */
    ItemFragment fragment;

    /**
     * Configuracion inicial del adaptador
     * @param items Lista de elementos
     * @param fragment Referencia al fragmento en el que esta la funcion de email
     */
    public MyItemRecyclerViewAdapter(List<BikesContent.Bike> items,ItemFragment fragment) {
        mValues = items;
        this.fragment=fragment;
    }


    /**
     * Creacion de vistas
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    /**
     * Rellenar una vista con los datos que tocan
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mImgPhoto.setImageBitmap(holder.mItem.getPhoto());
        holder.mTxtCity.setText(holder.mItem.getCity());
        holder.mTxtLocation.setText(holder.mItem.getLocation());
        holder.mTxtOwner.setText(holder.mItem.getOwner());
        holder.mDescription.setText(holder.mItem.getDescription());
        holder.mBtnMail.setOnClickListener(v -> fragment.mandarEmail(holder.mItem) );
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Clase de vistas de items
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public BikesContent.Bike mItem;
        public final ImageView mImgPhoto;
        public final TextView mTxtCity;
        public final TextView mTxtLocation;
        public final TextView mTxtOwner;
        public final TextView mDescription;
        public final ImageButton mBtnMail;


        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mImgPhoto = binding.imgPhoto;
            mTxtCity=binding.txtCity;
            mTxtLocation=binding.txtLocation;
            mTxtOwner=binding.txtOwner;
            mDescription=binding.txtDescription;
            mBtnMail=binding.btnMail;
        }
    }
}