package com.progtech.progtech2024.database.repositories;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.daos.AccountDao;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AccountRepository {
    private final AccountDao accountDao;
    private final ExecutorService executorService;

    public AccountRepository(AccountDao accountDao) {
        this.accountDao = accountDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public long register(Account account) throws ExecutionException, InterruptedException {
        Future<Long> newId = executorService.submit(() -> accountDao.register(account));
        return newId.get();
    }

    public boolean isUsernameAvailable(String username) throws ExecutionException, InterruptedException {
        Future<Boolean> isAvailable = executorService.submit(() -> accountDao.isUsernameAvailable(username));
        return isAvailable.get();
    }

    public Account login(String username, String password) throws ExecutionException, InterruptedException {
        Future<Account> account = executorService.submit(() -> accountDao.login(username, password));
        return account.get();
    }

    public int modifyBalance(String userId, int newBalance) throws ExecutionException, InterruptedException {
        Future<Integer> updatedRows = executorService.submit(() -> accountDao.modifyBalance(userId, newBalance));
        return updatedRows.get();
    }
}
