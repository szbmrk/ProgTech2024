package com.progtech.progtech2024.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.progtech.progtech2024.database.models.Account;

@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long register(Account account);

    @Query("SELECT 1 FROM accounts WHERE username = :username")
    long isUsernameAvailable(String username);

    @Query("SELECT * FROM accounts WHERE username = :username AND password = :password")
    Account login(String username, String password);

    @Query("UPDATE accounts SET balance = :newBalance WHERE id = :userId")
    int modifyBalance(long userId, int newBalance);
}
