package com.progtech.progtech2024.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TransactionDao {
    @Insert
    long insert(Transaction transaction);
}
