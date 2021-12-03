package com.unito.ium.progetto.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.unito.ium.progetto.data.Account;
import com.unito.ium.progetto.data.Corso;

@Dao
public interface AccountDao {

    @Insert
    public Long creaAccount(Account account);

    @Update
    public void aggiornaAccount(Account account);

    @Delete
    public void cancellaAccount(Account account);

    @Query("SELECT * FROM Account WHERE email= :email AND password= :password")
    public Account findAccountByEmailPassword(String email, String password);
}
