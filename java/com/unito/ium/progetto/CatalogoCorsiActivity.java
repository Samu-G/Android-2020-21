package com.unito.ium.progetto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.unito.ium.progetto.data.Account;
import com.unito.ium.progetto.data.AssociazioniCorsoDocente;
import com.unito.ium.progetto.data.Corso;
import com.unito.ium.progetto.data.Docente;
import com.unito.ium.progetto.db.Database;

public class CatalogoCorsiActivity extends AppCompatActivity {

    public static final String EXTRA_TITOLO_CORSO = "PassaggioTitoloCorso";
    public static final String EXTRA_DATA_CORSO = "PassaggioDataCorso";
    public static final String EXTRA_ORA_INIZIO_CORSO = "PassaggioOraInizioCorso";
    public static final String EXTRA_ORA_FINE_CORSO = "PassaggioOraFineCorso";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Catalogo ripetizioni");
    }

    @Override
    protected void onResume() {

        popolaDatabaseCorsi();
        popolaDatabaseAccount();
        popolaDatabaseAssociazioni();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CatalogoCorsiFragment fragment = new CatalogoCorsiFragment();
        fragmentTransaction.add(R.id.listaCorsi, fragment);
        fragmentTransaction.commit();

        super.onResume();
    }

    public void popolaDatabaseCorsi() {


        Corso a = new Corso();
        a.setTitoloCorso("ARCHITETTURA DEGLI ELABORATORI");
        a.setDataCorso("7/09/2020");
        a.setOraInizioCorso("15:00");
        a.setOraFineCorso("16:00");

        Corso b = new Corso();
        b.setTitoloCorso("ALGORITMI");
        b.setDataCorso("8/09/2020");
        b.setOraInizioCorso("16:00");
        b.setOraFineCorso("17:00");

        Corso c = new Corso();
        c.setTitoloCorso("IUM");
        c.setDataCorso("9/09/2020");
        c.setOraInizioCorso("16:00");
        c.setOraFineCorso("17:00");

        Corso d = new Corso();
        d.setTitoloCorso("PROGRAMMAZIONE 3");
        d.setDataCorso("10/09/2020");
        d.setOraInizioCorso("15:00");
        d.setOraFineCorso("17:00");

        if (Database.getInstance(getApplicationContext()).getCorsoDao().findCorso(a.getTitoloCorso()) == null) {
            Database.getInstance(getApplicationContext()).getCorsoDao().creaCorso(a);
        }

        if (Database.getInstance(getApplicationContext()).getCorsoDao().findCorso(b.getTitoloCorso()) == null) {
            Database.getInstance(getApplicationContext()).getCorsoDao().creaCorso(b);
        }

        if (Database.getInstance(getApplicationContext()).getCorsoDao().findCorso(c.getTitoloCorso()) == null) {
            Database.getInstance(getApplicationContext()).getCorsoDao().creaCorso(c);
        }

        if (Database.getInstance(getApplicationContext()).getCorsoDao().findCorso(d.getTitoloCorso()) == null) {
            Database.getInstance(getApplicationContext()).getCorsoDao().creaCorso(d);
        }

    }

    public void popolaDatabaseAccount() {

        Account a = new Account();
        a.setNome("Cristian");
        a.setCognome("Domenica");
        a.setEmail("cristian.domenica@gmail.com");
        a.setPassword("prova");
        a.setRuolo("Studente");

        Account b = new Account();
        b.setNome("Paolo");
        b.setCognome("Rossi");
        b.setEmail("paolo.rossi@gmail.com");
        b.setPassword("prova");
        b.setRuolo("Docente");
        Docente bb = new Docente();
        bb.setNomeDocente("Paolo");
        bb.setCognomeDocente("Rossi");


        Account c = new Account();
        c.setNome("Luca");
        c.setCognome("Torino");
        c.setEmail("luca.torino@gmail.com");
        c.setPassword("prova");
        c.setRuolo("Docente");
        Docente cc = new Docente();
        cc.setNomeDocente("Luca");
        cc.setCognomeDocente("Torino");

        Account d = new Account();
        d.setNome("Paolo");
        d.setCognome("Preite");
        d.setEmail("paolo.preite@gmail.com");
        d.setPassword("prova");
        d.setRuolo("Docente");
        Docente dd = new Docente();
        dd.setNomeDocente("Paolo");
        dd.setCognomeDocente("Preite");

        if (Database.getInstance(getApplicationContext()).getAccountDao().findAccountByEmailPassword(a.getEmail(), a.getPassword()) == null) {
            Database.getInstance(getApplicationContext()).getAccountDao().creaAccount(a);
        }

        if (Database.getInstance(getApplicationContext()).getAccountDao().findAccountByEmailPassword(b.getEmail(), b.getPassword()) == null) {
            Database.getInstance(getApplicationContext()).getAccountDao().creaAccount(b);
        }

        if (Database.getInstance(getApplicationContext()).getAccountDao().findAccountByEmailPassword(c.getEmail(), c.getPassword()) == null) {
            Database.getInstance(getApplicationContext()).getAccountDao().creaAccount(c);
        }

        if (Database.getInstance(getApplicationContext()).getAccountDao().findAccountByEmailPassword(d.getEmail(), d.getPassword()) == null) {
            Database.getInstance(getApplicationContext()).getAccountDao().creaAccount(d);
        }

        if (Database.getInstance(getApplicationContext()).getDocenteDao().findDocente(bb.getNomeDocente(), bb.getCognomeDocente()) == null) {
            Database.getInstance(getApplicationContext()).getDocenteDao().creaDocente(bb);
        }

        if (Database.getInstance(getApplicationContext()).getDocenteDao().findDocente(cc.getNomeDocente(), cc.getCognomeDocente()) == null) {
            Database.getInstance(getApplicationContext()).getDocenteDao().creaDocente(cc);
        }

        if (Database.getInstance(getApplicationContext()).getDocenteDao().findDocente(dd.getNomeDocente(), dd.getCognomeDocente()) == null) {
            Database.getInstance(getApplicationContext()).getDocenteDao().creaDocente(dd);
        }


    }


    public void popolaDatabaseAssociazioni() {

        AssociazioniCorsoDocente a = new AssociazioniCorsoDocente();
        a.setDocente(Database.getInstance(getApplicationContext()).getDocenteDao().findDocente("Paolo", "Rossi").getIdDocente());
        a.setCorso(Database.getInstance(getApplicationContext()).getCorsoDao().findCorso("ARCHITETTURA DEGLI ELABORATORI").getIdCorso());

        AssociazioniCorsoDocente b = new AssociazioniCorsoDocente();
        b.setDocente(Database.getInstance(getApplicationContext()).getDocenteDao().findDocente("Luca", "Torino").getIdDocente());
        b.setCorso(Database.getInstance(getApplicationContext()).getCorsoDao().findCorso("ARCHITETTURA DEGLI ELABORATORI").getIdCorso());

        AssociazioniCorsoDocente c = new AssociazioniCorsoDocente();
        c.setDocente(Database.getInstance(getApplicationContext()).getDocenteDao().findDocente("Paolo", "Preite").getIdDocente());
        c.setCorso(Database.getInstance(getApplicationContext()).getCorsoDao().findCorso("IUM").getIdCorso());

        AssociazioniCorsoDocente d = new AssociazioniCorsoDocente();
        d.setDocente(Database.getInstance(getApplicationContext()).getDocenteDao().findDocente("Paolo", "Rossi").getIdDocente());
        d.setCorso(Database.getInstance(getApplicationContext()).getCorsoDao().findCorso("ALGORITMI").getIdCorso());

        AssociazioniCorsoDocente e = new AssociazioniCorsoDocente();
        e.setDocente(Database.getInstance(getApplicationContext()).getDocenteDao().findDocente("Luca", "Torino").getIdDocente());
        e.setCorso(Database.getInstance(getApplicationContext()).getCorsoDao().findCorso("PROGRAMMAZIONE 3").getIdCorso());

        AssociazioniCorsoDocente f = new AssociazioniCorsoDocente();
        f.setDocente(Database.getInstance(getApplicationContext()).getDocenteDao().findDocente("Paolo", "Preite").getIdDocente());
        f.setCorso(Database.getInstance(getApplicationContext()).getCorsoDao().findCorso("IUM").getIdCorso());

        if (Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().cercaAssociazione(a.getCorso(), a.getDocente()) == null) {
            Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().creaAssociazione(a);
        }

        if (Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().cercaAssociazione(b.getCorso(), b.getDocente()) == null) {
            Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().creaAssociazione(b);
        }

        if (Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().cercaAssociazione(c.getCorso(), c.getDocente()) == null) {
            Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().creaAssociazione(c);
        }

        if (Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().cercaAssociazione(d.getCorso(), d.getDocente()) == null) {
            Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().creaAssociazione(d);
        }

        if (Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().cercaAssociazione(e.getCorso(), e.getDocente()) == null) {
            Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().creaAssociazione(e);
        }

        if (Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().cercaAssociazione(f.getCorso(), f.getDocente()) == null) {
            Database.getInstance(getApplicationContext()).getAssociazioniCorsoDocenteDao().creaAssociazione(f);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (LoginActivity.isLoggedIn == false) {
            //menu con solo login
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.opzioni_login, menu);
            return true;
        } else {
            MenuInflater inflater;

            switch (LoginActivity.ruoloLoggato) {

                case "Studente":
                    inflater = getMenuInflater();
                    inflater.inflate(R.menu.opzioni_account_studente, menu);
                    return true;

                case "Docente":
                    inflater = getMenuInflater();
                    inflater.inflate(R.menu.opzioni_account_docente, menu);
                    return true;


            }
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()) {

            case R.id.login:
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                break;

            case R.id.listaPrenotazioni:
                i = new Intent(getApplicationContext(), ListaPrenotazioniActivity.class);
                startActivity(i);
                break;

            case R.id.logout:
                LoginActivity.isLoggedIn = false;
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                break;

            case R.id.creaUnaRipezizione:
                i = new Intent(getApplicationContext(), CreaRipetizioneActivity.class);
                startActivity(i);
                break;

        }

        return true;
    }


}