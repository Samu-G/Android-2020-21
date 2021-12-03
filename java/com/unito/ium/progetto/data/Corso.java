package com.unito.ium.progetto.data;


import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Corsi")
public class Corso {
    @PrimaryKey(autoGenerate = true)
    private long idCorso;

    private String titoloCorso;
    private String dataCorso;
    private String oraInizioCorso;
    private String oraFineCorso;

    public long getIdCorso() {
        return idCorso;
    }

    public void setIdCorso(long idCorso) {
        this.idCorso = idCorso;
    }

    public String getTitoloCorso() {
        return titoloCorso;
    }

    public void setTitoloCorso(String titoloCorso) {
        this.titoloCorso = titoloCorso;
    }

    public String getDataCorso() {
        return dataCorso;
    }

    public void setDataCorso(String dataCorso) {
        this.dataCorso = dataCorso;
    }

    public String getOraInizioCorso() {
        return oraInizioCorso;
    }

    public void setOraInizioCorso(String oraInizioCorso) {
        this.oraInizioCorso = oraInizioCorso;
    }

    public String getOraFineCorso() {
        return oraFineCorso;
    }

    public void setOraFineCorso(String oraFineCorso) {
        this.oraFineCorso = oraFineCorso;
    }

}
