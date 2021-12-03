package com.unito.ium.progetto;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unito.ium.progetto.data.Corso;
import com.unito.ium.progetto.databinding.FragmentCatalogoCorsiBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCorsiRecyclerViewAdapter extends RecyclerView.Adapter<MyCorsiRecyclerViewAdapter.ViewHolder> {

    private final List<Corso> mValues;

    public MyCorsiRecyclerViewAdapter(List<Corso> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentCatalogoCorsiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.titoloCorso.setText(mValues.get(position).getTitoloCorso());
        holder.dataCorso.setText(mValues.get(position).getDataCorso());
        holder.oraInizioCorso.setText(mValues.get(position).getOraInizioCorso());
        holder.oraFineCorso.setText(mValues.get(position).getOraFineCorso());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titoloCorso;
        public final TextView dataCorso;
        public final TextView oraInizioCorso;
        public final TextView oraFineCorso;
        public Corso mItem;

        public ViewHolder(FragmentCatalogoCorsiBinding binding) {
            super(binding.getRoot());
            titoloCorso = binding.titoloCorso;
            dataCorso = binding.dataCorso;
            oraInizioCorso = binding.oraInizioCorso;
            oraFineCorso = binding.oraFineCorso;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), VisualizzaCorsoActivity.class);

                    i.putExtra(CatalogoCorsiActivity.EXTRA_TITOLO_CORSO, titoloCorso.getText().toString());
                    i.putExtra(CatalogoCorsiActivity.EXTRA_DATA_CORSO, dataCorso.getText().toString());
                    i.putExtra(CatalogoCorsiActivity.EXTRA_ORA_INIZIO_CORSO, oraInizioCorso.getText().toString());
                    i.putExtra(CatalogoCorsiActivity.EXTRA_ORA_FINE_CORSO, oraFineCorso.getText().toString());

                    v.getContext().startActivity(i);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titoloCorso.getText() + "'";
        }
    }
}