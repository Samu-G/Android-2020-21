package com.unito.ium.progetto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.unito.ium.progetto.data.Account;
import com.unito.ium.progetto.data.Docente;
import com.unito.ium.progetto.db.Database;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registra un ACCOUNT");
    }

    @Override
    protected void onResume() {

        setSpinnerRuolo();
        setRegisterButton();
        super.onResume();
    }

    private void setRegisterButton() {
        Button registerButton = findViewById(R.id.createAccountButton);
        TextView nameToRegister = findViewById(R.id.nameRegister);
        TextView surnameToRegister = findViewById(R.id.surnameRegister);
        TextView emailToRegister = (TextView) findViewById(R.id.emailRegister);
        TextView passwordToRegister = (TextView) findViewById(R.id.passwordRegister);
        Spinner roleToRegister = (Spinner) findViewById(R.id.spinnerRuolo);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nameToRegister.getText().equals("") &&
                        !surnameToRegister.getText().equals("") &&
                        !emailToRegister.getText().equals("") &&
                        !passwordToRegister.getText().equals("")) {
                    Account newAccount = new Account();

                    newAccount.setNome(nameToRegister.getText().toString());
                    newAccount.setCognome(surnameToRegister.getText().toString());
                    newAccount.setEmail(emailToRegister.getText().toString());
                    newAccount.setPassword(passwordToRegister.getText().toString());
                    newAccount.setRuolo(roleToRegister.getSelectedItem().toString());

                    if ((Database.getInstance(getApplicationContext()).getAccountDao().findAccountByEmailPassword(
                            newAccount.getEmail(), newAccount.getPassword())) == null
                    ) {

                        Database.getInstance(getApplicationContext()).getAccountDao().creaAccount(newAccount);

                        if(roleToRegister.getSelectedItem().toString().equals("Docente")) {

                            Docente newDocente = new Docente();

                            newDocente.setNomeDocente(nameToRegister.getText().toString());
                            newDocente.setCognomeDocente(surnameToRegister.getText().toString());

                            Database.getInstance(getApplicationContext()).getDocenteDao().creaDocente(newDocente);

                            Toast.makeText(getApplicationContext(),
                                    "Registrazione effettuata come DOCENTE",
                                    Toast.LENGTH_LONG)
                                    .show();

                        } else if(roleToRegister.getSelectedItem().toString().equals("Studente")) {
                            Toast.makeText(getApplicationContext(),
                                    "Registrazione effettuata come STUDENTE",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Account già esistente! Riprova con altre credenziali",
                                Toast.LENGTH_LONG)
                                .show();
                    }


                } else {
                    Toast.makeText(getApplicationContext(),
                            "Inserisci i dati correttamente!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private void setSpinnerRuolo() {
        Spinner ruolo = (Spinner) findViewById(R.id.spinnerRuolo);

        ArrayList<String> ruoli = new ArrayList<>();
        ruoli.add(0, "Studente");
        ruoli.add(1, "Docente");

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ruoli);

        /*Specifichiamo il layout da utilizzare per la visualizzazione della lista*/
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ruolo.setAdapter(arrayAdapter);
    }
}