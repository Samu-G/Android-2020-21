package com.unito.ium.progetto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.unito.ium.progetto.data.AssociazioniCorsoDocente;
import com.unito.ium.progetto.data.Corso;
import com.unito.ium.progetto.data.Docente;
import com.unito.ium.progetto.data.Prenotazione;
import com.unito.ium.progetto.db.Database;

import java.util.ArrayList;
import java.util.List;

public class VisualizzaCorsoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_corso);
        setTitle(getIntent().getStringExtra(CatalogoCorsiActivity.EXTRA_TITOLO_CORSO));
    }

    @Override
    protected void onResume() {

        impostaTestiDaIntent();
        impostaDocentiCheTengonoIlCorso();
        setPrenotaButton();

        super.onResume();
    }

    public void impostaTestiDaIntent() {
        Intent i = getIntent();

        TextView titoloCorso = findViewById(R.id.titoloCorsoVisualizza);
        TextView dataCorso = findViewById(R.id.dataCorsoVisualizza);
        TextView oraInizioCorso = findViewById(R.id.oraInizioCorsoVisualizza);
        TextView oraFineCorso = findViewById(R.id.oraFineCorsoVisualizza);


        titoloCorso.setText(i.getStringExtra(CatalogoCorsiActivity.EXTRA_TITOLO_CORSO));
        dataCorso.setText("Data: " + i.getStringExtra(CatalogoCorsiActivity.EXTRA_DATA_CORSO));
        oraInizioCorso.setText("Ora di inizio: " + i.getStringExtra(CatalogoCorsiActivity.EXTRA_ORA_INIZIO_CORSO));
        oraFineCorso.setText("Ora di fine: " + i.getStringExtra(CatalogoCorsiActivity.EXTRA_ORA_FINE_CORSO));
    }

    public void impostaDocentiCheTengonoIlCorso() {
        Intent i = getIntent();

        Corso corso = Database.getInstance(getApplicationContext())
                .getCorsoDao()
                .findCorsoDettagliato(
                        i.getStringExtra(CatalogoCorsiActivity.EXTRA_TITOLO_CORSO),
                        i.getStringExtra(CatalogoCorsiActivity.EXTRA_DATA_CORSO),
                        i.getStringExtra(CatalogoCorsiActivity.EXTRA_ORA_INIZIO_CORSO)
                );

        long idCorso = corso.getIdCorso();

        List<AssociazioniCorsoDocente> lista = Database.getInstance(getApplicationContext())
                .getAssociazioniCorsoDocenteDao()
                .trovaDocentiCheTengonoQuestoCorso(idCorso);

        ArrayList<Docente> d = new ArrayList<>();

        for (int a = 0; a < lista.size(); a++) {
            d.add(Database.getInstance(getApplicationContext())
                    .getDocenteDao()
                    .findDocenteById(lista.get(a).getDocente()));

            Log.d("---", d.get(a).getNomeDocente() + d.get(a).getCognomeDocente());
        }

        setSpinnerConDocenti(d);

        /*
        descrizioneCorso.setText(d.get(0).getNomeDocente() + " " + d.get(0).getCognomeDocente()
        + "\n" + d.get(1).getNomeDocente() + " " + d.get(1).getCognomeDocente());*/
    }

    public void setSpinnerConDocenti(ArrayList<Docente> arrayDocenti) {
        Spinner docenti = (Spinner) findViewById(R.id.spinnerDocenti);

        ArrayList<String> nomiCognomiDocenti = new ArrayList<>();
        for (int i = 0; i < arrayDocenti.size(); i++) {
            nomiCognomiDocenti.add(
                    arrayDocenti.get(i).getNomeDocente() + " " + arrayDocenti.get(i).getCognomeDocente()
            );
        }

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nomiCognomiDocenti);

        /*Specifichiamo il layout da utilizzare per la visualizzazione della lista*/
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        docenti.setAdapter(arrayAdapter);
    }

    public void setPrenotaButton() {

        Button button = findViewById(R.id.prenotaRipetizioneVisualizza);
        if (LoginActivity.isLoggedIn == true)
            button.setEnabled(true);
        else
            button.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.ruoloLoggato.equals("Studente")) {
                    if (Database.getInstance(getApplicationContext()).getPrenotazioniDao().trovaPrenotazione(
                            LoginActivity.emailLoggato,
                            getIntent().getStringExtra(CatalogoCorsiActivity.EXTRA_TITOLO_CORSO),
                            ((Spinner) findViewById(R.id.spinnerDocenti)).getSelectedItem().toString()
                    ) != null) {
                        Toast.makeText(getApplicationContext(),
                                "Ti sei già prenotato!",
                                Toast.LENGTH_LONG)
                                .show();
                    } else {
                        Prenotazione prenotazione = new Prenotazione();

                        prenotazione.setEmailPrenotato(LoginActivity.emailLoggato);
                        prenotazione.setDocenteScelto(((Spinner) findViewById(R.id.spinnerDocenti)).getSelectedItem().toString());
                        prenotazione.setTitoloCorso(getIntent().getStringExtra(CatalogoCorsiActivity.EXTRA_TITOLO_CORSO));
                        prenotazione.setDataCorso(getIntent().getStringExtra(CatalogoCorsiActivity.EXTRA_DATA_CORSO));
                        prenotazione.setOraInizioCorso(getIntent().getStringExtra(CatalogoCorsiActivity.EXTRA_ORA_INIZIO_CORSO));
                        prenotazione.setOraFineCorso(getIntent().getStringExtra(CatalogoCorsiActivity.EXTRA_ORA_FINE_CORSO));
                        prenotazione.setStatoDellaRipetizione("ATTIVA");

                        Database.getInstance(getApplicationContext()).getPrenotazioniDao().creaPrenotazione(prenotazione);

                        Toast.makeText(getApplicationContext(),
                                "Prenotazione avvenuta con successo",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                } else if (LoginActivity.ruoloLoggato.equals("Docente")) {
                    Toast.makeText(getApplicationContext(),
                            "Non puoi prenotarti come docente! Effettua il login come STUDENTE",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }

}