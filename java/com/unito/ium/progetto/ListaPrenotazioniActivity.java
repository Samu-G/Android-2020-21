package com.unito.ium.progetto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class ListaPrenotazioniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prenotazioni);
        setTitle("Prenotazioni di " + LoginActivity.emailLoggato);
    }

    @Override
    protected void onResume() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaPrenotazioniFragment fragment = new ListaPrenotazioniFragment();
        fragmentTransaction.add(R.id.listaPrenotazioni, fragment);
        fragmentTransaction.commit();

        super.onResume();
    }
}