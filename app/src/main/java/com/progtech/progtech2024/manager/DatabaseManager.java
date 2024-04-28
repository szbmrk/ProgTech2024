package com.progtech.progtech2024.manager;

import android.content.Context;

import androidx.room.Room;

import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.database.repositories.TransactionRepository;

import java.util.concurrent.ExecutionException;

public class DatabaseManager {
    public AccountRepository accountRepository() {
        return new AccountRepository(db.accountDao());
    }
    public TransactionRepository transactionRepository() {
        return new TransactionRepository(db.transactionDao());
    }

    public boolean PostTransaction(Transaction transaction) throws ExecutionException, InterruptedException {
        long newTransactionId = transactionRepository().insert(transaction);
        if (newTransactionId < 1)
            return false;
        return true;
    }

    private static DatabaseManager instance;
    private static volatile BankDatabase db;

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            synchronized (BankDatabase.class) {
                if (instance == null) {
                    db = Room.databaseBuilder(context.getApplicationContext(),
                                    BankDatabase.class, "bank").allowMainThreadQueries()
                            .build();
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }
}
