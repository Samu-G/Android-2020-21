package com.unito.ium.progetto.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.unito.ium.progetto.data.Corso;

import java.util.List;

@Dao
public interface CorsoDao {

    @Insert
    public Long creaCorso(Corso corso);

    @Update
    public void aggiornaCorso(Corso corso);

    @Delete
    public void cancellaCorso(Corso corso);

    @Query("SELECT * FROM Corsi WHERE idCorso= :id")
    public Corso findCorsoById(Long id);

    @Query("SELECT * FROM Corsi")
    public List<Corso> findAll();

    @Query("SELECT * FROM Corsi WHERE titoloCorso= :nome")
    public Corso findCorso(String nome);

    @Query("SELECT * FROM Corsi WHERE titoloCorso= :nome AND dataCorso= :data AND oraInizioCorso= :oraInizio")
    public Corso findCorsoDettagliato(String nome, String data, String oraInizio);
}
