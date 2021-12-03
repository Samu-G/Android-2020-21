package com.unito.ium.progetto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.unito.ium.progetto.data.AssociazioniCorsoDocente;
import com.unito.ium.progetto.data.Corso;
import com.unito.ium.progetto.db.Database;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreaRipetizioneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_ripetizione);
        setTitle("Crea una ripetizione come " + LoginActivity.emailLoggato);
    }

    @Override
    protected void onResume() {
        setCreaRipetizioneButton();
        super.onResume();
    }

    private void setCreaRipetizioneButton() {

        Button creaRipetizione = findViewById(R.id.creaRipetizioneButton);
        TextView titoloCorso = findViewById(R.id.titoloCorsoInserisci);
        TextView dataCorso = findViewById(R.id.dataCorsoInserisci);
        TextView oraInizioCorso = findViewById(R.id.oraInizioCorsoInserisci);
        TextView oraFineCorso = findViewById(R.id.oraFineCorsoInserisci);

        creaRipetizione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!titoloCorso.getText().equals("") &&
                        !dataCorso.getText().equals("") &&
                        !oraInizioCorso.getText().equals("") &&
                        !oraFineCorso.getText().equals("")) {

                    if (parseData(dataCorso.getText().toString()) &&
                            parseHour(oraInizioCorso.getText().toString()) &&
                            parseHour(oraFineCorso.getText().toString())) {
                        Corso nuovoCorso = new Corso();

                        nuovoCorso.setTitoloCorso(titoloCorso.getText().toString());
                        nuovoCorso.setDataCorso(dataCorso.getText().toString());
                        nuovoCorso.setOraInizioCorso(oraInizioCorso.getText().toString());
                        nuovoCorso.setOraFineCorso(oraFineCorso.getText().toString());

                        if ((Database.getInstance(getApplicationContext()).getCorsoDao().findCorsoDettagliato(
                                titoloCorso.getText().toString(),
                                dataCorso.getText().toString(),
                                oraInizioCorso.getText().toString()
                        )) == null) {

                            Long idCorsoCreato = Database.getInstance(getApplicationContext()).getCorsoDao().creaCorso(nuovoCorso);
                            Long idDocente = Database.getInstance(getApplicationContext()).getDocenteDao().findDocente(
                                    LoginActivity.nomeLoggato,
                                    LoginActivity.cognomeLoggato
                            ).getIdDocente();

                            AssociazioniCorsoDocente associazioniCorsoDocente = new AssociazioniCorsoDocente();
                            associazioniCorsoDocente.setCorso(idCorsoCreato);
                            associazioniCorsoDocente.setDocente(idDocente);

                            Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().creaAssociazione(associazioniCorsoDocente);

                            Toast.makeText(getApplicationContext(),
                                    "Completato! Ripetizione creata correttamente.",
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Attenzione! Esiste gia' un corso di questo tipo! Impossibile registrare il corso",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    } else {
                        if (!parseData(dataCorso.getText().toString())) {
                            Toast.makeText(getApplicationContext(),
                                    "Controlla di aver inserito la DATA correttamente",
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else if (!parseHour(oraInizioCorso.getText().toString())) {
                            Toast.makeText(getApplicationContext(),
                                    "Controlla di aver inserito l'ORA D'INIZIO correttamente",
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else if (!parseHour(oraFineCorso.getText().toString())) {
                            Toast.makeText(getApplicationContext(),
                                    "Controlla di aver inserito l'ORA DI FINE correttamente",
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "I dati sono stati inseriti in modo non corretto. Riprova inserendo i dati nei giusti formati",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Controlla di aver inserito tutti i campi",
                            Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

    }

    private boolean parseData(String date) {
        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    private boolean parseHour(String hour) {
        String regex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hour);
        return matcher.matches();
    }
}