package com.unito.ium.progetto;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.unito.ium.progetto.data.Prenotazione;
import com.unito.ium.progetto.databinding.FragmentListaPrenotazioniBinding;
import com.unito.ium.progetto.db.Database;

import org.w3c.dom.Text;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Prenotazione}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyListaPrenotazioniRecyclerViewAdapter extends RecyclerView.Adapter<MyListaPrenotazioniRecyclerViewAdapter.ViewHolder> {

    private final List<Prenotazione> mValues;

    public MyListaPrenotazioniRecyclerViewAdapter(List<Prenotazione> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentListaPrenotazioniBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.titoloCorso.setText(mValues.get(position).getTitoloCorso());
        holder.dataCorso.setText("Data: " + mValues.get(position).getDataCorso());
        holder.oraInizioCorso.setText("Ora di inizio: " + mValues.get(position).getOraInizioCorso());
        holder.oraFineCorso.setText("Ora di fine: " + mValues.get(position).getOraFineCorso());
        holder.docenteSelezionato.setText("Docente: " + mValues.get(position).getDocenteScelto());
        holder.statoPrenotazione.setText("Stato della prenotazione: " + mValues.get(position).getStatoDellaRipetizione());
        holder.idPrenotazione.setText(Long.toString(mValues.get(position).getIdPrenotazione()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public final TextView titoloCorso;
        public final TextView dataCorso;
        public final TextView oraInizioCorso;
        public final TextView oraFineCorso;
        public final TextView docenteSelezionato;
        public final TextView statoPrenotazione;
        public final TextView idPrenotazione;
        public Prenotazione mItem;

        public ViewHolder(FragmentListaPrenotazioniBinding binding) {
            super(binding.getRoot());
            titoloCorso = binding.titoloCorsoPrenotazioni;
            dataCorso = binding.dataCorsoPrenotazioni;
            oraInizioCorso = binding.oraInizioCorsoPrenotazioni;
            oraFineCorso = binding.oraFineCorsoPrenotazioni;
            docenteSelezionato = binding.docenteCorsoPrenotazioni;
            statoPrenotazione = binding.statoPrenotazione;
            idPrenotazione = binding.idPrenotazione;
            binding.getRoot().setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            ListaPrenotazioniActivity activity = (ListaPrenotazioniActivity) v.getContext();

            MenuInflater inflater = new MenuInflater(activity);
            inflater.inflate(R.menu.opzioni_lista_prenotazioni, menu);

            MenuItem impostaComeEffettuata = menu.findItem(R.id.menuEffettuata);
            impostaComeEffettuata.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    statoPrenotazione.setText("Stato della prenotazione: " + "EFFETTUATA");
                    Database.getInstance(v.getContext()).getPrenotazioniDao()
                            .updateStato("EFFETTUATA",
                                    Long.parseLong(idPrenotazione.getText().toString())
                            );
                    return false;
                }
            });

            MenuItem impostaComeDisdetta = menu.findItem(R.id.menuDisdici);
            impostaComeDisdetta.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    statoPrenotazione.setText("Stato della prenotazione: " + "DISDETTA");
                    Database.getInstance(v.getContext()).getPrenotazioniDao()
                            .updateStato("DISDETTA",
                                    Long.parseLong(idPrenotazione.getText().toString())
                            );
                    return false;
                }
            });

        }



    }
}