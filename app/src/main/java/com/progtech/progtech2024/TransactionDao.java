package com.progtech.progtech2024;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);
}
