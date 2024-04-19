package com.progtech.progtech2024.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Account.class, Transaction.class}, version = 1)
public abstract class BankDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
    public abstract TransactionDao transactionDao();
}
