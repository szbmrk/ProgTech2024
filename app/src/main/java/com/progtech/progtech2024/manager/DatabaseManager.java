package com.progtech.progtech2024.manager;

import android.content.Context;

import androidx.room.Room;

import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.daos.AccountDao;
import com.progtech.progtech2024.database.daos.TransactionDao;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.database.repositories.TransactionRepository;

import java.util.concurrent.ExecutionException;

public class DatabaseManager {

    public AccountRepository GetAccountRepository() {
        return new AccountRepository(db.accountDao());
    }

    public TransactionRepository GetTransactionRepository() {
        return  new TransactionRepository(db.transactionDao());
    }

    public AccountRepository GetTestAccountRepository() {
        return new AccountRepository(testDb.accountDao());
    }

    public TransactionRepository GetTestTransactionRepository() {
        return new TransactionRepository(testDb.transactionDao());
    }

    private static volatile DatabaseManager instance;
    private static volatile BankDatabase db;
    private static volatile BankDatabase testDb;

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            synchronized (BankDatabase.class) {
                if (instance == null) {
                    db = Room.databaseBuilder(context.getApplicationContext(),
                                    BankDatabase.class, "bank").allowMainThreadQueries()
                            .build();

                    testDb = Room.databaseBuilder(context.getApplicationContext(),
                                    BankDatabase.class, "bank_test").allowMainThreadQueries()
                            .build();
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }
}
