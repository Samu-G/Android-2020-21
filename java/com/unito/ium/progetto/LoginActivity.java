package com.unito.ium.progetto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.unito.ium.progetto.data.Account;
import com.unito.ium.progetto.db.Database;

public class LoginActivity extends AppCompatActivity {

    public static boolean isLoggedIn = false;
    public static String nomeLoggato = null;
    public static String cognomeLoggato = null;
    public static String emailLoggato = null;
    public static String ruoloLoggato = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Effettua il LOGIN");
        setLoginButton();
        setRegisterButton();
    }

    private void setRegisterButton() {
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void setLoginButton() {
        Button button = findViewById(R.id.loginButton);
        TextView email = findViewById(R.id.editTextTextEmailAddress);
        TextView password = findViewById(R.id.editTextTextPassword);
        final Account[] account = new Account[1];

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controlla campo email che non sia null
                //controlla campo password che non sia null
                if (!email.getText().toString().equals("") &&
                        !password.getText().toString().equals("")
                ) {
                    Log.d("---", "email e pass corretta");


                    account[0] = Database.getInstance(getApplicationContext()).getAccountDao().findAccountByEmailPassword(
                            email.getText().toString(),
                            password.getText().toString());


                    if (account[0] == null) {
                        isLoggedIn = false;

                        Log.d("---", "account inesistente");
                        Toast.makeText(getApplicationContext(),
                                "account inesistente",
                                Toast.LENGTH_LONG)
                                .show();
                    } else {
                        isLoggedIn = true;
                        nomeLoggato = account[0].getNome();
                        cognomeLoggato = account[0].getCognome();
                        emailLoggato = account[0].getEmail();
                        ruoloLoggato = account[0].getRuolo();

                        Log.d("---", "LOGIN EFFETTUATO");
                        Toast.makeText(getApplicationContext(),
                                "LOGIN EFFETTUATO",
                                Toast.LENGTH_LONG)
                                .show();

                        Intent i = new Intent(getApplicationContext(), CatalogoCorsiActivity.class);
                        startActivity(i);
                    }
                } else {
                    Log.d("---", email.getText().toString() + "  " + password.getText().toString());
                }


                //accedi al database

                //controlla se ce un login-password associato nel database
                //se si
                //metto un avviso login con successo
                //passo al CatalogoCorsiActivity
                //cambio valore variabile di login a true
                //se no
                //id o utente password sbagliati
            }
        });

    }
}