package com.progtech.progtech2024.database.repositories;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.daos.AccountDao;

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

    public Future<Long> register(Account account) {
        return executorService.submit(() -> accountDao.register(account));
    }

    public Future<Boolean> isUsernameAvailable(String username) {
        return executorService.submit(() -> accountDao.isUsernameAvailable(username));
    }

    public Future<Account> login(String username, String password) {
        return executorService.submit(() -> accountDao.login(username, password));
    }

    public Future<Integer> modifyBalance(int userId, int newBalance) {
        return executorService.submit(() -> accountDao.modifyBalance(userId, newBalance));
    }
}
