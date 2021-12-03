package com.unito.ium.progetto.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.unito.ium.progetto.data.Corso;
import com.unito.ium.progetto.data.Docente;

import java.util.List;

@Dao
public interface DocenteDao {

    @Insert
    public Long creaDocente(Docente docente);

    @Query("SELECT * FROM Docenti WHERE idDocente= :id")
    public Docente findDocenteById(Long id);

    @Query("SELECT * FROM Docenti WHERE nomeDocente= :nome AND cognomeDocente= :cognome")
    public Docente findDocente(String nome, String cognome);
}
