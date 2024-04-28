package com.progtech.progtech2024.database.repositories;

import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.daos.TransactionDao;

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

    public Future<Long> insert(Transaction transaction) {
        return executorService.submit(() -> transactionDao.insert(transaction));
    }
}
