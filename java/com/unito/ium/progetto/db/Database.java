package com.unito.ium.progetto.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.unito.ium.progetto.dao.AccountDao;
import com.unito.ium.progetto.dao.AssociazioniCorsoDocenteDao;
import com.unito.ium.progetto.dao.CorsoDao;
import com.unito.ium.progetto.dao.DocenteDao;
import com.unito.ium.progetto.dao.PrenotazioniDao;
import com.unito.ium.progetto.data.Account;
import com.unito.ium.progetto.data.AssociazioniCorsoDocente;
import com.unito.ium.progetto.data.Corso;
import com.unito.ium.progetto.data.Docente;
import com.unito.ium.progetto.data.Prenotazione;

@androidx.room.Database(
        entities = {Corso.class, Account.class, Docente.class, AssociazioniCorsoDocente.class, Prenotazione.class},
        version = 1
)
public abstract class Database extends RoomDatabase {

    public static Database database;

    public abstract CorsoDao getCorsoDao();

    public abstract AccountDao getAccountDao();

    public abstract DocenteDao getDocenteDao();

    public abstract PrenotazioniDao getPrenotazioniDao();

    public abstract AssociazioniCorsoDocenteDao getAssociazioniCorsoDocenteDao();

    public static Database getInstance(Context context) {
        if(database == null) {
            database = Room.databaseBuilder(
                    context,
                    Database.class,
                    "database,db"
            ).allowMainThreadQueries()
                    .build();
        }
        return database;
    }

}
