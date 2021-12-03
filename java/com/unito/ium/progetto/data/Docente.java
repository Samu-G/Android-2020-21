package com.unito.ium.progetto.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

@Entity(tableName = "Docenti",
        indices = @Index(value = {"cognomeDocente"}, unique = true))
public class Docente {

    @PrimaryKey(autoGenerate = true)
    private long idDocente;


    private String nomeDocente;

    private String cognomeDocente;

    public long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(long idDocente) {
        this.idDocente = idDocente;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public String getCognomeDocente() {
        return cognomeDocente;
    }

    public void setCognomeDocente(String cognomeDocente) {
        this.cognomeDocente = cognomeDocente;
    }
}
