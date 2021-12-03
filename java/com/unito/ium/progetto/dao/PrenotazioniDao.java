package com.unito.ium.progetto.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.unito.ium.progetto.data.Prenotazione;

import java.util.List;

@Dao
public interface PrenotazioniDao {

    @Insert
    public Long creaPrenotazione(Prenotazione prenotazione);

    @Delete
    public void cancellaPrenotazione(Prenotazione prenotazione);

    @Query("SELECT * FROM Prenotazioni WHERE  emailPrenotato= :email ")
    public List<Prenotazione> trovaPrenotazioni(String email);

    @Query("SELECT * FROM Prenotazioni WHERE  emailPrenotato= :email AND titoloCorso= :id AND docenteScelto= :docente ")
    public Prenotazione trovaPrenotazione(String email, String id, String docente);

    @Query("UPDATE Prenotazioni SET statoDellaRipetizione=:nuovoStato WHERE idPrenotazione=:id")
    public void updateStato(String nuovoStato, long id);
}
