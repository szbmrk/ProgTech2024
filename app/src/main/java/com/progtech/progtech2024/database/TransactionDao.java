package com.progtech.progtech2024.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    long insert(Transaction transaction);

    @Query("SELECT * FROM TRANSACTIONS WHERE fromAccountId = :userId OR toAccountId = :userId")
    List<Transaction> getTransactionsForUser(int userId);
}
