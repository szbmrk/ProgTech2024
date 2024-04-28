package com.progtech.progtech2024.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AccountDao {
    @Insert
    void register(Account account);

    @Query("SELECT EXISTS(SELECT 1 FROM accounts WHERE username = :username)")
    boolean isUsernameAvailable(String username);

    @Query("SELECT * FROM accounts WHERE username = :username AND password = :password")
    Account login(String username, String password);

    @Query("UPDATE accounts SET balance = :newBalance WHERE id = :userId")
    boolean modifyBalance(int userId, int newBalance);
}
