package com.progtech.progtech2024.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.progtech.progtech2024.database.models.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Transaction transaction);

    @Query("SELECT * FROM TRANSACTIONS WHERE fromAccountId = :userId OR toAccountId = :userId")
    List<Transaction> getTransactionsForUser(int userId);
}
