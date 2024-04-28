package com.progtech.progtech2024.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.progtech.progtech2024.database.daos.AccountDao;
import com.progtech.progtech2024.database.daos.TransactionDao;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.database.repositories.TransactionRepository;

@Database(entities = {Account.class, Transaction.class}, version = 1)
public abstract class BankDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
    public abstract TransactionDao transactionDao();

    public AccountRepository accountRepository() {
        return new AccountRepository(accountDao());
    }
    public TransactionRepository transactionRepository() {
        return new TransactionRepository(transactionDao());
    }

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
