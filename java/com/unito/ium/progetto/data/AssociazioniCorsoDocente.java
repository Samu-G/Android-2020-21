package com.unito.ium.progetto.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AssociazioniCorsoDocente")
public class AssociazioniCorsoDocente {

    @PrimaryKey(autoGenerate = true)
    private long idAssociazione;

    private long corso;
    private long docente;

    public long getIdAssociazione() {
        return idAssociazione;
    }

    public void setIdAssociazione(long idAssociazione) {
        this.idAssociazione = idAssociazione;
    }

    public long getCorso() {
        return corso;
    }

    public void setCorso(long corso) {
        this.corso = corso;
    }

    public long getDocente() {
        return docente;
    }

    public void setDocente(long docente) {
        this.docente = docente;
    }
}
