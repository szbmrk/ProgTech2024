package com.progtech.progtech2024.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Account.class, Transaction.class}, version = 1)
public abstract class BankDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
    public abstract TransactionDao transactionDao();

    private static volatile BankDatabase instance;

    public static BankDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (BankDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    BankDatabase.class, "bank").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}
