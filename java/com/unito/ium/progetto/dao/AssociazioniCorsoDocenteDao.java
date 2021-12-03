package com.unito.ium.progetto.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unito.ium.progetto.data.AssociazioniCorsoDocente;
import com.unito.ium.progetto.data.Corso;
import com.unito.ium.progetto.data.Docente;

import java.util.List;

@Dao
public interface AssociazioniCorsoDocenteDao {

    @Insert
    public Long creaAssociazione(AssociazioniCorsoDocente associazioniCorsoDocente);

    @Query("SELECT * FROM AssociazioniCorsoDocente WHERE corso= :corsoCercato AND docente= :docenteCercato")
    public AssociazioniCorsoDocente cercaAssociazione(long corsoCercato, long docenteCercato);

    @Query("SELECT * FROM AssociazioniCorsoDocente WHERE corso= :corsoCercato")
    public List<AssociazioniCorsoDocente> trovaDocentiCheTengonoQuestoCorso(long corsoCercato);

}
