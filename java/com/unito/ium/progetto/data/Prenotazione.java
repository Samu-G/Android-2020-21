package com.unito.ium.progetto.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Prenotazioni")
public class Prenotazione {

    @PrimaryKey(autoGenerate = true)
    private long idPrenotazione;

    private String titoloCorso;
    private String dataCorso;
    private String oraInizioCorso;
    private String oraFineCorso;
    private String emailPrenotato;
    private String docenteScelto;
    private String statoDellaRipetizione;

    public long getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(long idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
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

    public String getEmailPrenotato() {
        return emailPrenotato;
    }

    public void setEmailPrenotato(String emailPrenotato) {
        this.emailPrenotato = emailPrenotato;
    }

    public String getDocenteScelto() {
        return docenteScelto;
    }

    public void setDocenteScelto(String docenteScelto) {
        this.docenteScelto = docenteScelto;
    }

    public String getStatoDellaRipetizione() {
        return statoDellaRipetizione;
    }

    public void setStatoDellaRipetizione(String statoDellaRipetizione) {
        this.statoDellaRipetizione = statoDellaRipetizione;
    }
}
