package com.progtech.progtech2024.database.repositories;

import android.content.Context;

import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.daos.TransactionDao;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TransactionRepository {
    private final TransactionDao transactionDao;
    private final ExecutorService executorService;

    public TransactionRepository(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public boolean PostTransaction(Transaction transaction) throws ExecutionException, InterruptedException {
        Future<Long> newId = executorService.submit(() -> transactionDao.insert(transaction));
        return newId.get() > 0;
    }

    public List<Transaction> GetTransactionsForAccount(int accountId) throws ExecutionException, InterruptedException {
        Future<List<Transaction>> transactions = executorService.submit(() -> transactionDao.getTransactionsForUser(accountId));
        return transactions.get();
    }
}
