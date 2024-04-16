package com.progtech.progtech2024;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AccountDao {
    @Insert
    void register(Account account);

    @Query("SELECT * FROM accounts WHERE username = :username AND password = :password")
    Account login(String username, String password);
}
